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
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public @ResponseBody Map<List<String>,Response> sendConfigMap() throws ProcessingException, IOException {
		logger.trace("Configuration map accessed.");
		return configfile.getConfigMap();
	}
	
	@RequestMapping("/**")
    public @ResponseBody byte[] defaultResp(@RequestBody byte[] request)  throws ProcessingException, IOException {
		
		 String reqStr = new String(request);	
		 ObjectMapper mapper = new ObjectMapper();
		 Request req = new Request();
		 try{
			 req = mapper.readValue(reqStr, Request.class);
		 } catch (IOException e) {
			 logger.trace("Error occurred while valiating Request Body");
			 e.printStackTrace();
		 }
		 
		 String method = req.getMethod();
		 String url = req.getUrl();
		 Requestheaders reqHeader = req.getRequestheaders();
		 Requestpayload reqPayload = req.getRequestpayload();
//		 String finalUrl = (String) request.getAttribute(
//			        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//		 Map<String, String[]> paramap = request.getParameterMap();
//		 
//		 if(!paramap.isEmpty()) {
//			 finalUrl += '?';
//			 for(Entry<String, String[]> elem: paramap.entrySet()) {
//				 finalUrl += (String)elem.getKey() + "=";
//				 String[] paramval = (String[])elem.getValue();
//				 finalUrl += paramval[0];
//			 }	 
//		 }
//		 
//		finalUrl = finalUrl.substring(10, finalUrl.length());
//		Map<List<String>,Response> configmap = configfile.getConfigMap();
		byte[] response = null;
		
//		for (Entry<List<String>, Response> mapElement: configmap.entrySet()) { 
//			
//            List<String> key = (List<String>) mapElement.getKey();
//            String url = key.get(1);
//            System.out.println("Url here is  = " + url);
//            System.out.println("request url here is " + finalUrl);
//            if(url.equals(finalUrl)) {
//            	logger.trace("Found request url in configuration map.");
//            	String resp = "Hi, you got your static running";
//            	response = resp.getBytes();
//            	break;
//            }
//        } 
      
        return response;
        
        // Read the URL
        // match the URL and header from the conf.
        // fetch right end conf for this given case.
        
        //home/aayush/Programming/Mastercard/springide-workspace/.metadata/.plugins/org.eclipse.jdt.ui/jdt-images/19.png
        //send a dubby response.
    }
}
