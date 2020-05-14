package com.simulator.application.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ConfigService {
	
	public List<String> defaultThings() {
		List<String> defaultBody = new ArrayList<String> ();
		defaultBody.add("thing1");
		defaultBody.add("thing2");
		defaultBody.add("thing3");
		return defaultBody;
	}
}
