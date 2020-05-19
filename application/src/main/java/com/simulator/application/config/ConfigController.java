package com.simulator.application.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.*;

@Controller
@RequestMapping("/simulator")
public class ConfigController {
	
	static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

	@Autowired
	public ConfigurationLoader configfile;
	
	@RequestMapping("/config")
	public @ResponseBody Config sendConfig() throws ProcessingException, IOException {
		logger.trace("Configuration file viewed.");
		return configfile.getConfig();
	}
	
	@RequestMapping("/configmap")
	public @ResponseBody Map<List<String>, String> sendConfigMap() throws ProcessingException, IOException {
		logger.trace("Configuration map accessed.");
		return configfile.getConfigMap();
	}
	
	@RequestMapping("/**")
    public @ResponseBody byte[] defaultResp(@RequestBody(required = false) byte[] bytes, HttpServletRequest request)  throws ProcessingException, IOException {
			
		
		RestJsonImplementor jsonImp = new RestJsonImplementor(bytes, request);
		jsonImp.reqMarshaling();
		jsonImp.createResponse();
		byte[] response = jsonImp.getResponseStream();
		return response;
	}
}