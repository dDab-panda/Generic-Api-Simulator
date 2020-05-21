package com.simulator.application.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.library.LibraryApplication;
import com.simulator.pojo.*;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationLoader {
	
	private static Config config;
	private static Map< List<String>, String> ConfigMap;
	
	public Config getConfig() {
		return config;	
	}
	public static Map<List<String>, String> getConfigMap(){
		return ConfigMap;
	}
	
	@PostConstruct
	public void loadConfig() throws ProcessingException, IOException {
		File jsonFile = new File(getClass().getClassLoader().getResource("config.json").getFile());
		config = LibraryApplication.getJsonObj(jsonFile);
		
		Map<List<String>, String> mymap = new HashMap< List<String>, String>();
		
		List<Application> apps = config.getApplications();
		
		for(Application app: apps) {
			String cntxt = app.getContext();
			//String type = app.getType();
			
			List<Endpoint> endpoints = app.getEndpoints();
			
			for(Endpoint endp : endpoints) {
				String method = endp.getRequest().getMethod();
				String reqUrl = endp.getRequest().getUrl();
				String finalUrl = cntxt + reqUrl;
				String respMapping = endp.getResponseMapping();
				List<String> key = new ArrayList<String> ();
				key.add(method);
				key.add(finalUrl);
				key.add(endp.getRequest().getRequestheaders().toString());
				
				mymap.put(key, respMapping);
			}
		}		
		ConfigMap = mymap;
	}
}
