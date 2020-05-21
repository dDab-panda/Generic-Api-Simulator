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
import com.simulator.util.JsonToMapConvertorUtil;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationLoader {
	
	private static Config config;
	private static Map<List<String>, Endpoint> EndPointMap;
	private static Map<List<String>, Application> ApplicationMap;
	
	public Config getConfig() {
		return config;	
	}
	
	public static Map<List<String>, Endpoint> getEndPointMap() {
		return EndPointMap;
	}
	
	public static Map<List<String>, Application> getApplicationMap() {
		return ApplicationMap;
	}
	
	@PostConstruct
	public void loadConfig() throws ProcessingException, IOException {
		File jsonFile = new File(getClass().getClassLoader().getResource("config.json").getFile());
		config = LibraryApplication.getJsonObj(jsonFile);
		
		// Two maps - one to store end points and one to store applications
		Map<List<String>, Endpoint> EndPointMap = new HashMap<List<String>, Endpoint>();
		Map<List<String>, Application> AppMap = new HashMap<List<String>, Application>();
		
		List<Application> apps = config.getApplications();
		
		for(Application app: apps) {
			String cntxt = app.getContext();			
			List<Endpoint> endpoints = app.getEndpoints();
			
			for(Endpoint endp : endpoints) {
				String method = endp.getRequest().getMethod();
				String reqUrl = endp.getRequest().getUrl();
				String finalUrl = cntxt + reqUrl;
				List<String> EndptKey = new ArrayList<String> ();
				List<String> AppKey = new ArrayList<String> ();

				Map<String, String> reqHeaders = JsonToMapConvertorUtil.convertJsonToMap(
						endp.getRequest().getRequestheaders().toString());
				String acceptType = reqHeaders.get("Accept");

				EndptKey.add(method);
				EndptKey.add(finalUrl);
				EndptKey.add(acceptType);
				EndPointMap.put(EndptKey, endp);
				
				AppKey.add(method);
				AppKey.add(finalUrl);
				AppKey.add(acceptType);
				AppMap.put(AppKey, app);
			}
		}		
		
	}
}
