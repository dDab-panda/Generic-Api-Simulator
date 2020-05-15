package com.simulator.application.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/{context}/{reqUrl}")
	public List<String> defaultResp(@PathVariable String context, @PathVariable String reqUrl) throws ProcessingException, IOException {
		if(context + "/" + reqUrl == ConfigLoader.getPath()) {
			return confServ.getConfig().getApplications().getEndpoints().get(0).getResponse().getBody();
		}
		else return null;
	}
}