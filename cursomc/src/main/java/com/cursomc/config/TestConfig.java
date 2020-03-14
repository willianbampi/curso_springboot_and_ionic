package com.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public boolean instatiateDatabase() throws ParseException {
		databaseService.instantiateDatabase();
		return true;
	}

}