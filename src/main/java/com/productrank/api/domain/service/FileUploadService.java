package com.productrank.api.domain.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

//@Service
//@Slf4j
public class FileUploadService {
/*
    private final AmazonS3Client amazonS3Client;

    public FileUploadService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ResponseEntity<String> uploadFile(String urlStr) {
        try {
            URL imgaeUrl = new URL(urlStr);
            ImageIO.read(imgaeUrl);

            File file = new File();

            String fileName = file.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            String fileUrl = "https://" + bucket + "/" + uuid;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            amazonS3Client.putObject(new PutObjectRequest(bucket, uuid.toString(), file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return ResponseEntity.ok(amazonS3Client.getUrl(bucket, uuid.toString()).toString());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
*/
}
