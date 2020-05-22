package com.simulator.application.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.controller.EndpointController;
import com.simulator.library.LibraryApplication;
import com.simulator.pojo.*;

import javax.annotation.PostConstruct;

import com.simulator.pojo.config.ConfigKey;
import com.simulator.pojo.config.ConfigValue;
import com.simulator.pojo.response.mapping.ResponseMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationLoader {
	static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);
	private static Config config;
	private static Map<ConfigKey, ConfigValue> configMap;
	private static List<ResponseMapping> responseMapping;
	//private static ResponseMapping responseMappingsingle;

	/*public ResponseMapping getResponseMappingsingle() {
		return responseMappingsingle;
	}*/

	public Map<ConfigKey, ConfigValue> getConfigMap(){
		return configMap;
	}

	public Config getConfig() {
		return config;
	}

	public List<ResponseMapping> getResponseMapping(){
		return responseMapping;
	}

	@PostConstruct
	public void loadConfig() throws ProcessingException, IOException {
		File jsonFile = new File(getClass().getClassLoader().getResource("config.json").getFile());
		config = LibraryApplication.getJsonObj(jsonFile);
		logger.trace("configFile loaded");

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
		logger.trace("configMap loaded");
		String magicFilepath = System.getProperty("user.dir");
		//TODO: change the logic to get file from class path
		magicFilepath+="/application/src/main/resources/magic/EmployeeMagicData.json";
		//File magicFile = new File("/home/aayush/Programming/Mastercard/Generic-Api-Simulator/application/src/main/resources/magic/EmployeeMagicData.json");
		/*ResponseMapping object =null;
		File magicFile = new File(object.getClass().getClassLoader().getResource("EmployeeMagicData.json").getFile());
		*/
		File magicFile = new File(magicFilepath);
		this.responseMapping = ResponseMapping.getMappingFromMagicFile(magicFile);
		logger.trace("Response map loaded");



	}

}
