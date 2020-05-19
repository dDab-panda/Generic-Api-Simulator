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
import com.simulator.pojo.Response;

public class RestJsonImplementor implements RequestHandler {
	
	byte[] reqbody;
	HttpServletRequest request;
	Response resp;
	String response;

	public RestJsonImplementor(byte[] reqbody, HttpServletRequest request) {
		super();
		this.reqbody = reqbody;
		this.request = request;
	}
	
	public String getBody() {
		String str = new String(reqbody);
		return str;
	}
	
	public String getPath() {
		String finalPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		Map<String, String[]> paramap = request.getParameterMap();

		if(!paramap.isEmpty()) {
			finalPath += '?';
			for(Entry<String, String[]> elem: paramap.entrySet()) {
				finalPath += (String)elem.getKey() + "=";
				String[] paramval = (String[])elem.getValue();
				finalPath += paramval[0];
			 }	 
		}
		finalPath = finalPath.substring(10, finalPath.length());
		return finalPath;
	}
	
	@Override
	public void preProcess() {
		
	}
	
	@Override
	public void reqValidation() {
		
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
	public void createResponse() {
		List<String> key = new ArrayList<String> ();
		key.add(reqMarshaling().getMethod());
		key.add(getPath());
		
		if(ConfigurationLoader.getConfigMap().containsKey(key)) {
			response = ConfigurationLoader.getConfigMap().get(key).toString();
		}
		else response = null;
	}
	
	@Override
	public byte[] getResponseStream() {
		return response.getBytes();
	}
}
