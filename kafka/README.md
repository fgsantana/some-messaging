Producer and consumer using Apache Kafka and Springboot

There's an endpoint on the Producer to send a message via HTTP POST method and an endpoint on the Consumer to list all received messages via HTTP GET method.

Creating a topic:

> `kafka-topics --create --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1 --topic test-topic`

*partitions and replication-factor are only 1 in this example, however in some cases it's important to analyze and use higher values(scalability and high availability)* 