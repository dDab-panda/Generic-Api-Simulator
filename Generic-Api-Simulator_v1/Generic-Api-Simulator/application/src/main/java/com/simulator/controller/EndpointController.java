package com.simulator.controller;

import java.io.IOException;
//import javax.inject.Provider;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.simulator.application.config.ConfigurationLoader;
import com.simulator.handler.IRequestHandler;
import com.simulator.handler.RequetHandlerFactory;
import com.simulator.service.RequestContext;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private void CreatetRequestContext(HttpServletRequest request, byte[] requestBody){
		//get the application and endpoint from conf
		getRequestContext().setApplication(null); //TODO
		getRequestContext().setEndpoint(null); //TODO
		getRequestContext().setMethod(null);//TODO
		getRequestContext().setUrl(null);//TODO
		getRequestContext().setRequestHeaders(null);//TODO
		getRequestContext().setQueryParams(null);//TODO
		getRequestContext().setRequestPayloads(null);//TODO
		getRequestContext().setByteStream(requestBody);

	}

	private String getPath(HttpServletRequest request) {
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

	private RequestContext getRequestContext(){
		return requestContext;
	}
}