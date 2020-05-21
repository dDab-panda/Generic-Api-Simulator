package com.simulator.controller;

import java.io.IOException;
//import javax.inject.Provider;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.simulator.application.config.ConfigurationLoader;
import com.simulator.exceptions.NoEndpointFoundException;
import com.simulator.handler.IRequestHandler;
import com.simulator.handler.RequetHandlerFactory;
import com.simulator.pojo.config.ConfigKey;
import com.simulator.pojo.config.ConfigValue;
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
	public ConfigurationLoader configurationLoader;

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
		getRequestContext().setByteStream(requestBody);
		getRequestContext().setMethod(request.getMethod());
		getRequestContext().setUrl(getPath(request));
		getRequestContext().setType("REST");

		ConfigValue configValue  = getConfigValue();
		if(configValue == null){
			throw new NoEndpointFoundException();
		}
		getRequestContext().setApplication(configValue.getApplication());
		getRequestContext().setEndpoint(configValue.getEndpoint());
		getRequestContext().setRequestHeaders(null);//TODO
		getRequestContext().setQueryParams(null);//TODO
	}

	private ConfigValue getConfigValue(){
		ConfigKey key = new ConfigKey(
				getRequestContext().getType(),
				getRequestContext().getUrl(),
				getRequestContext().getMethod());

		return configurationLoader.getConfigMap().get(key);
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