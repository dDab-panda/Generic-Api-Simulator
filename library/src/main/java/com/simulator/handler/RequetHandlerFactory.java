package com.simulator.handler;

import com.simulator.service.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public final class RequetHandlerFactory {

    @Autowired
    List<IRequestHandler> handlers;

    @Autowired
    private RequestContext requestContext;

    public IRequestHandler getHandler() {
        RequestContext ccontext = getRequestContext();
        //Todo - get accept type header
        //REST/SOAP
        //Method
        return handlers.get(0); //TODO: return right return type
    }

    private RequestContext getRequestContext() {
        return requestContext;
    }
}