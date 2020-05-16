package com.simulator.library;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.*;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/simulator")
public class GenericApiController {

	@Autowired
	public ConfigurationLoader configfile;
	
	@RequestMapping("/config")
	public @ResponseBody Config sendConfig() throws ProcessingException, IOException {
		return configfile.getConfig();
	}
	
	@RequestMapping("/{reqUrl}")
    public @ResponseBody Response defaultResp(@PathVariable String reqUrl) throws ProcessingException, IOException {
		
		
		
		
		Map<List<String>,Response> configmap = configfile.getConfigMap();
		Response response = new Response();
		return response;
		/*
		//Iterator<Entry<List<String>, Response>> mapElement;
		//byte[] response=null;
		//String resp = "Hi, you got your static running with url = "+ reqUrl;
    	//response = resp.getBytes();
    	
		for (Entry<List<String>, Response> mapElement: configmap.entrySet()) { 
			
            List<String> key = (List<String>) mapElement.getKey();
            String url = key.get(1);
            if(url == reqUrl) {
            	//String resp = "Hi, you got your static running";
            	response = (Response) mapElement.getValue();
            	break;
            }
        } 
	
        return response;
        */
		
        //Read the URL
        // match the URL and header from the conf.
        // fetch right end conf for this given case.
        
        //home/aayush/Programming/Mastercard/springide-workspace/.metadata/.plugins/org.eclipse.jdt.ui/jdt-images/19.png
        //send a dubby response.
        
    }
	
}
