package com.simulator.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.pojo.response.mapping.MagicData;
import com.simulator.pojo.response.mapping.RequestType;
import com.simulator.pojo.response.mapping.ResponseMapping;
import com.simulator.service.RequestContext;
import com.simulator.util.JsonToMapConvertorUtil;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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


    }

    @Override
    public void reqValidation() {

    }

    @Override
    public void createResponse() {
        Map<String,String> headersFromContext =  requestContext.getRequestHeaders();
        for(Map.Entry<String,String> elem : headersFromContext.entrySet()){
            String k = (String) elem.getKey();
            String v = (String) elem.getValue();
            logger.info("Headers from context === "+ k+" = " +v);
        }
        
               List<ResponseMapping> responseMappingList = requestContext.getResponseMappingList();


        Map<String,String> payloadFromContext = requestContext.getRequestPayloads();
        if(payloadFromContext!=null){

            for (ResponseMapping responseMappingElem:responseMappingList){
                List<MagicData> magicDataList = responseMappingElem.getMagicdata();
                JSONObject magicDataResponse =  responseMappingElem.getResponse();
                Map<String,String> headersFromMagic = extractHeadersFromMagicData(magicDataList);
                Map<String,String> payloadFromMagic = extractPayloadFromMagicData(magicDataList);

                boolean checkPayload = false;
                boolean checkHeaders = true;

                if(payloadFromMagic.equals(payloadFromContext)) checkPayload=true;

                for(Map.Entry<String,String> elem : headersFromMagic.entrySet()){
                    String key= (String) elem.getKey();
                    if(!headersFromContext.containsKey(key)){
                        checkHeaders=false;
                        break;
                    }
                }

                if(checkHeaders && checkPayload){
                    String responseFromMagic = magicDataResponse.toString();
                    requestContext.setResponse(responseFromMagic);
                    break;
                }

            }

        }
        logger.info("request payload received {}", requestContext.getRequestPayloads());
    }

    @Override
    public byte[] getRes    ponseStream() {

        return requestContext.getResponse().getBytes();
    }

    public static Map<String,String> extractHeadersFromMagicData(List<MagicData> magicDataList){
        Map<String,String> headerMap = new HashMap<>();
        for(MagicData elem : magicDataList){

            if(elem.getType()== RequestType.header){
                headerMap.put(elem.getName(),elem.getValue());
            }
        }
        return headerMap;
    }

    public static Map<String,String> extractPayloadFromMagicData(List<MagicData> magicDataList){
        Map<String,String> payloadMap = new HashMap<>();
        for(MagicData elem : magicDataList){
            if(elem.getType()==RequestType.request){
                
                payloadMap.put(elem.getName(),elem.getValue());
            }
        }
        return payloadMap;
    }

}
