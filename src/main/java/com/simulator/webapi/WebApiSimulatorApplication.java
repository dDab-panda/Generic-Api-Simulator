package com.simulator.webapi;

import java.io.File;
import java.io.IOException;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApiSimulatorApplication {

	public static void main(String[] args) throws IOException, ParseException, ProcessingException {
		
		// loading data from file into JSON object		
	//	JSONParser parser = new JSONParser();
		
	//	FileReader reader = new FileReader("/Mastercard/emp/src/main/resources/EmployeeData.json");
		
		//Object obj = parser.parse(reader);
		
	//	JSONObject empjsonobj = (JSONObject)obj;
		
		// Validating the JSON object
		File schemaFile = new File("src/main/resources/schema.json");
		File jsonFile = new File("src/main/resources/config.json");
		
		if (ValidationUtils.isJsonValid(schemaFile, jsonFile)){
	    	System.out.println("Valid!");
	    }else{
	    	System.out.println("NOT valid!");
	    }
		
		// 	SpringApplication.run(EmpApplication.class, args);
	}
}
