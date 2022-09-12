package amazonsqsproducer

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClient

object SQSConfig {



    fun awsSQS() : AmazonSQS{
        val region = System.getenv("AWS_SQS_REGION")

        return AmazonSQSClient.builder().withCredentials(awsCredentials()).withRegion(region).build()
    }

    fun awsCredentials(): AWSCredentialsProvider {
        val accessKey = System.getenv("AWS_SQS_ACESS_KEY")
        val secretKey = System.getenv("AWS_SQS_SECRET_KEY")

        return AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey))
    }


}