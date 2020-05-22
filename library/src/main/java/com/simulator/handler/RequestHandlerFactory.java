package com.simulator.handler;

import com.simulator.service.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public final class RequestHandlerFactory {

    @Autowired
    List<IRequestHandler> handlers;

    @Autowired
    private RequestContext requestContext;

    public IRequestHandler getHandler() {
       RequestContext context = getRequestContext();
       /*  String acceptType = context.getRequestHeaders().get("Accept");
        String type = context.getApplication().getType();

        if(type == "REST")
        {
            return handlers.get(0);
        }
       */ return handlers.get(0);
    }

    private RequestContext getRequestContext() {
        return requestContext;
    }
}