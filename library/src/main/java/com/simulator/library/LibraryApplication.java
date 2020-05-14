package com.simulator.library;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.Config;

public class LibraryApplication {
	
	public static Config getJsonObj(File jsonFile) throws ProcessingException, IOException {
		// load the configuration JSON file into the an object
		// of the (already) defiened POJO class
		Config cnf = null;

		if(ValidationUtils.isJsonValidNew(jsonFile)) {
			System.out.println("Valid configuration!");
		}
		else {
			System.out.println("Invalid configuration!");
			return cnf;
		}
		
		ObjectMapper mapper = new ObjectMapper();

		try {
			cnf = mapper.readValue(jsonFile, Config.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	    return cnf;
	}
}