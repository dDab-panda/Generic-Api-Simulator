package com.simulator.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.pojo.response.mapping.ResponseMapping;
import com.simulator.service.RequestContext;
import com.simulator.util.JsonToMapConvertorUtil;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RestJsonRequestHandler implements IRequestHandler {
    static final Logger logger = LoggerFactory.getLogger(RestJsonRequestHandler.class);

    @Autowired
    RequestContext requestContext;

    @Override
    public void preProcess() {

    }

    @Override
    public void reqMarshaling() throws JSONException, ParseException, JsonProcessingException {
        logger.trace("ReqMarshling func enter");
        if (null == requestContext.getByteStream()) {
            return;
        }

        requestContext.setRequestPayloads(
                JsonToMapConvertorUtil.convertJsonToMap(
                        new String(requestContext.getByteStream()))
        );
        Map<String,String> payloadsss = requestContext.getRequestPayloads();

        for(Map.Entry<String,String> elem : payloadsss.entrySet()){
            String k = (String) elem.getKey();
            String v = (String) elem.getValue();
            logger.trace("Payloads = "+ k+" == " +v);
        }

    }

    @Override
    public void reqValidation() {

    }

    @Override
    public void createResponse() {
        /*Map<String,String> payload = requestContext.getRequestPayloads();
        if(payload!=null){
            Map<String,String> headers =  requestContext.getRequestHeaders();
            List<ResponseMapping> responseMappingList = requestContext.getResponseMappingList();
        }*/
        logger.info("request payload received {}", requestContext.getRequestPayloads());
    }

    @Override
    public byte[] getResponseStream() {
        return "Hello World!!".getBytes();
    }
}
