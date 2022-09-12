package amazonsqsproducer


import com.amazonaws.services.sqs.model.SendMessageRequest

import io.jooby.Kooby
import io.jooby.runApp


fun main(args: Array<String>) {
    runApp(args, Server::class)
}

class Server : Kooby({

    val queueUrl = System.getenv("AWS_SQS_QUEUE_URL")

    post("/messages") {

        val sqsClient = SQSConfig.awsSQS()
        val textBody = ctx.body().value()

        val messageRequestToSQS = SendMessageRequest()
            .withQueueUrl(queueUrl)
            .withMessageBody(textBody)

        sqsClient.sendMessage(messageRequestToSQS).messageId

    }
})



