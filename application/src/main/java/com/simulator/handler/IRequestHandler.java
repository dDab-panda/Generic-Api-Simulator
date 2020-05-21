package com.simulator.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.pojo.Request;

public interface IRequestHandler {
	
	public void preProcess();
	public void reqMarshaling() throws JSONException, ParseException, JsonProcessingException;
	public void reqValidation();
	public void createResponse();
	public byte[] getResponseStream();
}
