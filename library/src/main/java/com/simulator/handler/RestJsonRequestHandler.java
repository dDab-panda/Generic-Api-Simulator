package com.simulator.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.pojo.response.mapping.MagicData;
import com.simulator.pojo.response.mapping.RequestType;
import com.simulator.pojo.response.mapping.ResponseMapping;
import com.simulator.service.RequestContext;
import com.simulator.util.JsonToMapConvertorUtil;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        List<ResponseMapping> responseMappings = requestContext.getEndpoint().getResponseMappings();
        for(ResponseMapping resMap : responseMappings) {
            boolean flag = true;
            for(MagicData magicdata: resMap.getMagicdata()) {
                if(! isMagicDataMatching(magicdata)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                requestContext.setResponse(resMap.getMagicResponse().getResponse());
            }
            else {
            	requestContext.setResponse("Response not found.");
            }
        }
        logger.info("request payload received {}", requestContext.getRequestPayloads());
    }

    @Override
    public byte[] getResponseStream() {
        String response = StringUtils.isEmpty(requestContext.getResponse()) ? "" : requestContext.getResponse();
        return response.getBytes();
    }

    private boolean isMagicDataMatching(MagicData magicData){
        Map<String, String> map = null;
        if(magicData.getType() == RequestType.header){
            map = requestContext.getRequestHeaders();
        } else if(magicData.getType() == RequestType.request){
            map = requestContext.getRequestPayloads();
        }
        if(magicData.getValue().equals(
                map.get(magicData.getName()))) {
            return true;
        } else {
            return false;
        }
    }
}
