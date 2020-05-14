package com.simulator.application.config;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.library.LibraryApplication;
import com.simulator.pojo.Config;

@Service
public class ConfigLoader {
	static Config conf;
	
	@PostConstruct
	public static Config getConfig() throws ProcessingException, IOException { 
		File jsonFile = new File("src/main/resources/config.json");
		conf = LibraryApplication.getJsonObj(jsonFile);
		return conf;
	}
}
