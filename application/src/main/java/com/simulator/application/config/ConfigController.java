package com.simulator.application.config;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.pojo.*;

@RestController
public class ConfigController {
	
	@RequestMapping("/config")
	public Response getResp() {
		
		// load the configuration JSON file into the an object
		// of the (already) defiened POJO class
		ObjectMapper mapper = new ObjectMapper();
		Config cnf = null;

		try {
			cnf = mapper.readValue(new File("src/main/resources/config.json"), Config.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// for now, our implementation gives a very simple response
		// which is same as the one in first endpoint in first application
	    return cnf.getApplications().getEndpoints().get(0).getResponse();
	}
}