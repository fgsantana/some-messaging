package main

import (
	"encoding/json"
	"fmt"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/sqs"
	"log"
	"net/http"
	"os"
)

var (
	allMessages []MessageResponse

	messageTimeout       = aws.Int64(5)
	waitTime             = aws.Int64(1)
	queueUrl             = aws.String(os.Getenv("AWS_SQS_QUEUE_URL"))
	maxMessagesToReceive = aws.Int64(1)
)

type MessageResponse struct {
	Content   string `json:"content"`
	MessageId string `json:"messageId"`
}

func main() {
	go receiveMessages()

	http.HandleFunc("/messages", handleMessages)
	http.ListenAndServe(":8081", nil)

}

func handleMessages(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(allMessages)
}

func receiveMessages() {

	sess := session.Must(session.NewSessionWithOptions(session.Options{
		SharedConfigState: session.SharedConfigEnable,
	}))
	sqsClient := sqs.New(sess)

	for {

		msgs, err := sqsClient.ReceiveMessage(&sqs.ReceiveMessageInput{
			AttributeNames:        []*string{aws.String(sqs.MessageSystemAttributeNameSentTimestamp)},
			MessageAttributeNames: []*string{aws.String(sqs.QueueAttributeNameAll)},
			QueueUrl:              queueUrl,
			MaxNumberOfMessages:   maxMessagesToReceive,
			VisibilityTimeout:     messageTimeout,
			WaitTimeSeconds:       waitTime,
		})

		if err != nil {
			log.Panicln("Error receiving messages", err)
		}

		messages := msgs.Messages

		if len(messages) > 0 {

			for _, message := range messages {

				receiptHandle, messageId := message.ReceiptHandle, message.MessageId

				fmt.Printf("Received message: id - %s | body - %s \n", *messageId, *message.Body)

				allMessages = append(allMessages, MessageResponse{Content: *message.Body, MessageId: *messageId})

				deleteRes, err := sqsClient.DeleteMessage(&sqs.DeleteMessageInput{
					QueueUrl:      queueUrl,
					ReceiptHandle: receiptHandle,
				})

				if err != nil {
					log.Panicln("Error deleting the message")
				}
				log.Printf(" \n Message with receiptHandler: %s deleted | messageId: %s", *receiptHandle, *messageId)

				log.Printf("\n %s", deleteRes.String())
			}
		}

	}
}
