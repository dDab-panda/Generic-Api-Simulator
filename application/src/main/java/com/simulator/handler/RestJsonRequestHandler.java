package com.simulator.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.Helper.Helper;
import com.simulator.service.RequestContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RestJsonRequestHandler implements IRequestHandler {

    @Autowired
    RequestContext requestContext;

    @Override
    public void preProcess() {

    }

    @Override
    public void reqMarshaling() throws JSONException, ParseException, JsonProcessingException {
        if (null == requestContext.getByteStream()) {
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        String message = new String(requestContext.getByteStream());
        Map<String, String> requestMap = mapper.readValue(message, new TypeReference<Map<String, String>>() {
        });
        //return requestMap;
        //String message = new String(requestContext.getByteStream());
       /* //JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = new JSONObject(message);
        } catch (JSONException err) {
            //Log.d("Error", err.toString());
        }*/
        //        JSONObject jsonObject = (JSONObject) parser.parse(message);
        //Map<String, String> resultMap = Helper.parseJSONObjectToMap(jsonObject);
        requestContext.setRequestPayloads(requestMap);
    }

    @Override
    public void reqValidation() {

    }

    @Override
    public void createResponse() {

    }

    @Override
    public byte[] getResponseStream() {
        return "Hello World!!".getBytes();
    }
}
