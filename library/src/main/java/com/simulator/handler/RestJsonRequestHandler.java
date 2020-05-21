package com.simulator.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.simulator.service.RequestContext;
import com.simulator.util.JsonToMapConvertorUtil;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        logger.info("request payload received {}", requestContext.getRequestPayloads());
    }

    @Override
    public byte[] getResponseStream() {
        return "Hello World!!".getBytes();
    }
}
