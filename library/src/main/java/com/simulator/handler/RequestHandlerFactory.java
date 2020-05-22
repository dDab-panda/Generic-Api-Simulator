package com.simulator.handler;

import com.simulator.controller.EndpointController;
import com.simulator.service.RequestContext;
import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public final class RequestHandlerFactory {

    static final Logger logger = LoggerFactory.getLogger(RequestHandlerFactory.class);
    @Autowired
    List<IRequestHandler> handlers;

    @Autowired
    private RequestContext requestContext;

    public IRequestHandler getHandler() {
        RequestContext context = getRequestContext();
        Map<String,String> reqHeaders =  (Map<String,String>) context.getRequestHeaders();
        String RestOrSoap = context.getType();

        String reqTypeFile = reqHeaders.get("Accept");
        reqTypeFile = reqTypeFile.substring(13,reqTypeFile.length());
        logger.trace("type = "+ RestOrSoap);
        logger.trace("json/xml = " + reqTypeFile);
        //REST/SOAP
        //Method
        if(RestOrSoap == "REST") {
            if (reqTypeFile == "json") return handlers.get(0);
            else return handlers.get(1);
        }
        else return  handlers.get(2);

        //TODO: return right return type
    }

    private RequestContext getRequestContext() {
        return requestContext;
    }
}