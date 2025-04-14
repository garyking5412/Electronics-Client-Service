package com.example.electronicsspringbootclientservice.aws;

import com.example.electronicsspringbootclientservice.config.S3Config;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;

//@Component
@AllArgsConstructor
public class AwsS3Client {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final S3Client s3Client;

    public AwsS3Client() {
        s3Client = S3Config.s3ClientBuilder();
    }

    public void sendRequest() {
        createBucket(s3Client, "takemi-kazuchi-test-bucket");
        s3Client.close();
    }

    public void createBucket(S3Client client, String bucketName) {
        CreateBucketRequest request = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        logger.info(">>> creating bucket with name: [{}]", bucketName);
        try {
            client.createBucket(request);
            client.waiter().waitUntilBucketExists(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());
            logger.info("bucket [{}] is ready >>>", bucketName);
        } catch (Exception e) {
            logger.error(">>> creating bucket with name: [{}] failed with exception: {}", bucketName, e.getMessage());
        }
    }
}
