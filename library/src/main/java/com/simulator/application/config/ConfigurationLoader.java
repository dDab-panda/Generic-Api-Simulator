package com.simulator.application.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.library.LibraryApplication;
import com.simulator.pojo.*;

import javax.annotation.PostConstruct;

import com.simulator.pojo.config.ConfigKey;
import com.simulator.pojo.config.ConfigValue;
import com.simulator.pojo.response.mapping.MagicResponse;
import com.simulator.pojo.response.mapping.ResponseMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ConfigurationLoader {
	static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);
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
		logger.trace("configFile loaded");

		Map<ConfigKey, ConfigValue> configMap = new HashMap<>();
		
		for(Application app: config.getApplications()) {
			String cntxt = app.getContext();

			for(Endpoint endp : app.getEndpoints()) {
				ConfigKey key = new ConfigKey(app.getType(),
						cntxt + endp.getRequest().getUrl(),
						endp.getRequest().getMethod());
				if(! StringUtils.isEmpty(endp.getResponseMapping())){
					File magicDataConfig = new File(getClass().getClassLoader().getResource(endp.getResponseMapping()).getFile());
					endp.setResponseMappings( getMappingFromMagicFile(magicDataConfig) );
				}
				configMap.put(key, new ConfigValue(app, endp));
			}
		}
		this.configMap = configMap;
		logger.info("configMap loaded");
	}


	public static List<ResponseMapping> getMappingFromMagicFile(File magicFile){
		List<ResponseMapping> responseMappingList=null;
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer( MagicResponse.class, new CustomMagicResponseMappingDeserializer());
		mapper.registerModule(module);

		try {
			responseMappingList = mapper.readValue(magicFile, new TypeReference<List<ResponseMapping>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMappingList;
	}


}
