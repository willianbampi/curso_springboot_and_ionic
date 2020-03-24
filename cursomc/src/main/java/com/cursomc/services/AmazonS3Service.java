package com.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonS3Service {
	
	@Autowired
	private AmazonS3 amazonS3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(AmazonS3Service.class);
	
	public void uploadFile(String localFilePath) {
		try {
			File file = new File(localFilePath);
			LOG.info("Upload started");
			amazonS3Client.putObject(new PutObjectRequest(bucketName, "teste", file));
			LOG.info("Upload finished");
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: " + e.getMessage());
			LOG.info("Status code: " + e.getErrorCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " + e.getMessage());
		}
	}
	
}