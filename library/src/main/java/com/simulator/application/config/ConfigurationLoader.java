package com.simulator.application.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.library.LibraryApplication;
import com.simulator.pojo.*;

import javax.annotation.PostConstruct;

import com.simulator.pojo.config.ConfigKey;
import com.simulator.pojo.config.ConfigValue;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationLoader {
	
	private static Config config;
	private static Map<ConfigKey, ConfigValue> configMap;

	public Map<ConfigKey, ConfigValue> getConfigMap(){
		return configMap;
	}

	public Config getConfig() {
		return config;
	}

	@PostConstruct
	public void loadConfig() throws ProcessingException, IOException {
		File jsonFile = new File(getClass().getClassLoader().getResource("config.json").getFile());
		config = LibraryApplication.getJsonObj(jsonFile);
		
		Map<ConfigKey, ConfigValue> configMap = new HashMap<>();
		
		for(Application app: config.getApplications()) {
			String cntxt = app.getContext();

			for(Endpoint endp : app.getEndpoints()) {
				ConfigKey key = new ConfigKey(app.getType(),
						cntxt + endp.getRequest().getUrl(),
						endp.getRequest().getMethod());

				configMap.put(key, new ConfigValue(app, endp));
			}
		}

		this.configMap = configMap;
	}
}
