Running producer:

> 1. `./gradlew build`
> 2. `java -jar build/libs/amazon-sqs-producer-1.0.0-all.jar`

or

> `./gradlew joobyRun`

It's possible to send HTTP request (POST - text/plain) to send a message in http://localhost:8080/messages
  
  .
  
Running consumer:

> 1. `go mod tidy`
> 2. `go run main.go`

List with all messages received: http://localhost:8081/messages