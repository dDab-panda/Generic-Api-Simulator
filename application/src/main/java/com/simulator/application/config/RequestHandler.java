package com.simulator.application.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.pojo.Request;

public interface RequestHandler {
	
	public void preProcess();
	public Request reqMarshaling();
	public void reqValidation();
	public void createResponse();
	public byte[] getResponseStream();
}
