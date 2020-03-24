package com.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cursomc.services.exceptions.FileException;

@Service
public class AmazonS3Service {
	
	@Autowired
	private AmazonS3 amazonS3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(AmazonS3Service.class);
	
	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream inputStream;
			inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("IO error - " + e.getMessage());
		}
	}
	
	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(contentType);
			LOG.info("Upload started");
			amazonS3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
			LOG.info("Upload finished");
			return amazonS3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Converter URL to URI error - " + e.getMessage());
		}
	}
	
}