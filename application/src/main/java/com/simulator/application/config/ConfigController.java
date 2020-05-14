package com.simulator.application.config;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.*;

@RestController
public class ConfigController {
	
	@RequestMapping("/config")
	public Config getResp() throws ProcessingException, IOException {
	    return ConfigLoader.getConfig();
	}
}