package com.simulator.application.config;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.Config;

@Service
public class ConfigService {
	
	public Config getConfig() throws ProcessingException, IOException {
		return ConfigLoader.getConfig();
	}
}