package com.simulator.application;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.library.ValidationUtils;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ProcessingException, IOException {
		String configpath = System.getProperty("user.dir");
		configpath+="/src/main/resources/config.json";
		System.out.println(configpath);
		File jsonFile = new File(configpath);
		
		if(ValidationUtils.isJsonValidNew(jsonFile)) {
			System.out.println("Valid configuration!");
			
			SpringApplication.run(Application.class, args);
		}
		else {
			System.out.println("Invalid configuration!");
		}
	}

}
