package com.simulator;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ProcessingException, IOException {
		
		SpringApplication.run(Application.class, args);
	}
}
