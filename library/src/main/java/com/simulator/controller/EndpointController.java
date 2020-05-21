package com.simulator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
//import javax.inject.Provider;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.simulator.application.config.ConfigurationLoader;
import com.simulator.handler.IRequestHandler;
import com.simulator.handler.RequetHandlerFactory;
import com.simulator.service.RequestContext;
import com.simulator.util.JsonToMapConvertorUtil;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.pojo.*;

@Controller
@RequestMapping("/simulator")
public class EndpointController {
	
	static final Logger logger = LoggerFactory.getLogger(EndpointController.class);

	@Autowired
	public ConfigurationLoader configfile;

	@Autowired
	private RequestContext requestContext;

	@Autowired
	private RequetHandlerFactory requestHandlerFactory;

	
	@RequestMapping("/**")
    public @ResponseBody byte[] defaultResp(@RequestBody(required = false) byte[] bytes, HttpServletRequest request) throws ProcessingException, IOException, JSONException, ParseException {
		CreatetRequestContext(request, bytes);

		IRequestHandler handler = requestHandlerFactory.getHandler();

		handler.reqMarshaling();
		handler.createResponse();
		return handler.getResponseStream();
	}

	private void CreatetRequestContext(HttpServletRequest request, byte[] requestBody) throws JsonProcessingException {
		// get the application and endpoint from conf
		List<String> key = new ArrayList<String> ();
		key.add(request.getMethod());
		key.add(request.getRequestURI());
		key.add(request.getHeader("Accept"));
		
		// set application
		Application app = ConfigurationLoader.getApplicationMap().get(key);
		getRequestContext().setApplication(app); 
		
		// set end point
		Endpoint endp = ConfigurationLoader.getEndPointMap().get(key);
		getRequestContext().setEndpoint(endp);
		
		// set method
		getRequestContext().setMethod(request.getMethod());
		
		// set URL
		getRequestContext().setUrl(request.getRequestURI());
		
		// set request headers
		Map<String, String> reqheaders = new HashMap<String, String>();
		Enumeration<String> headerNames = request.getHeaderNames();	
		List<String>  headerList = Collections.list(headerNames);
    	for(String headerName: headerList) {
    		reqheaders.put(headerName, request.getHeader(headerName));
    	}
		getRequestContext().setRequestHeaders(reqheaders);
		
		// set query parameters
		getRequestContext().setQueryParams(request.getParameterMap());
		
		// set request payload
		String reqStr = new String(requestBody);
		getRequestContext().setRequestPayloads(JsonToMapConvertorUtil.convertJsonToMap(reqStr));	
		
		// set request payload in terms of byte array
		getRequestContext().setByteStream(requestBody);

	}

	// NOT NEEDED
//	private String getPath(HttpServletRequest request) {
//		String path = request.getRequestURI();
//		Map<String, String[]> paramap = request.getParameterMap();
//
//		if(!paramap.isEmpty()) {
//			path += '?';
//			for(Entry<String, String[]> elem: paramap.entrySet()) {
//				path += (String)elem.getKey() + "=";
//				String[] paramval = (String[])elem.getValue();
//				path += paramval[0];
//			}
//		}
//
//		String finalPath = path.substring(10, path.length());
//		return finalPath;
//	}

	private RequestContext getRequestContext(){
		return requestContext;
	}
}