package com.cursomc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Configuration {
	
	@Value("${aws.access_key_id}")
	private String awsId;
	
	@Value("${aws.secret_access_key}")
	private String awsKey;
	
	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3 amazonS3Client() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
		return amazonS3Client;
	}
}