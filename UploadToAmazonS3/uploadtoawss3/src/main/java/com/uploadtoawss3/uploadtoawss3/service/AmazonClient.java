package com.uploadtoawss3.uploadtoawss3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class AmazonClient {
    private final AmazonS3 s3Client;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    public AmazonClient(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            throw new RuntimeException("File upload Failed: " + e.getMessage());
        }
    }

    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        try {
            s3Client.deleteObject(bucketName, fileName);
            return "File deleted successfully!";
        } catch (Exception e) {
            throw new RuntimeException("File deletion failed: " + e.getMessage());
        }
    }
}
