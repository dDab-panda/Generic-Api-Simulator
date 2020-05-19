package com.simulator.application.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.pojo.Request;
import com.simulator.pojo.Requestheaders;
import com.simulator.pojo.Response;

public class RestJsonImplementor implements RequestHandler {
	
	public class RequestContext {
		String Endptconf;
	    Requestheaders requestHeaders;
	    Map<String, String> requestPayloads;
	    Map<String, String[]> queryParams;
	    Map<MagicData, Response> magicDataResponse;
	    
	    public RequestContext(byte[] reqbytes, HttpServletRequest request) {
	    	
	    	// Convert reqbytes to an object of type Request
	    	String requestString = new String(reqbytes);
	    	ObjectMapper mapper = new ObjectMapper();
			Request req = new Request();
			try {
				req = mapper.readValue(requestString, Request.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	    	// Setting end point configuration
	    	Endptconf = request.getRequestURI();
	    	
	    	// setting Request headers
	    	requestHeaders = req.getRequestheaders();
	    	
	    	// setting query parameters
	    	queryParams = request.getParameterMap();
	    	
	    	
	    }
	}
	
	// *******************************************************************************
	
	public String getPath() {
		String path = request.getRequestURI();
		Map<String, String[]> paramap = request.getParameterMap();
	 
		 if(!paramap.isEmpty()) {
			 path += '?';
			 for(Entry<String, String[]> elem: paramap.entrySet()) {
				 path += (String)elem.getKey() + "=";
				 String[] paramval = (String[])elem.getValue();
				 path += paramval[0];
			 }	 
		 }
	 
		 String finalPath = path.substring(10, path.length());
		 return finalPath;
	}
	
	@Override
	public void preProcess() {
		
	}

	@Override
	public Request reqMarshaling() {
		ObjectMapper mapper = new ObjectMapper();
		Request req = new Request();
		try{
			req = mapper.readValue(getBody(), Request.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return req;
	}
	
	@Override
	public void reqValidation() {
		
	}
	
	@Override
	public void createResponse() {
		List<String> key = new ArrayList<String> ();
		key.add(reqMarshaling().getMethod());
		key.add(getPath());
		
		System.out.println(key);
		
		if(ConfigurationLoader.getConfigMap().containsKey(key)) {
			response = "dummy response";
		}
		else response = "";
	}
	
	@Override
	public byte[] getResponseStream() {
		return response.getBytes();
	}
}
