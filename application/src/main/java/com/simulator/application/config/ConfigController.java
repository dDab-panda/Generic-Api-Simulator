package com.simulator.application.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.*;

@RestController
@RequestMapping("/api")
public class ConfigController {
	
	@Autowired
	public ConfigService confServ;
	
	@RequestMapping("/config")
	public Config getResp() throws ProcessingException, IOException {
	    return ConfigLoader.getConfig();
	}
	
	@RequestMapping("/search")
	public List<String> defaultResp() {
		return confServ.defaultThings();
	}
}