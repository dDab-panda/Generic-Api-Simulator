package com.simulator.service;

import java.util.List;
import java.util.Map;

import com.simulator.pojo.Application;
import com.simulator.pojo.Endpoint;
import com.simulator.pojo.response.mapping.ResponseMapping;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestContext {
    private Endpoint endpoint;
    private Application application;
    private String url;
    private String method;
    private String type;
    private Map<String,String> requestHeaders;
    private Map<String,String> requestPayloads;
    private Map<String,String[]> queryParams;
    private byte[] byteStream;
    private List<ResponseMapping> responseMappingList;

    public byte[] getByteStream() {
        return byteStream;
    }

    public void setByteStream(byte[] byteStream) {
        this.byteStream = byteStream;
    }

    public List<ResponseMapping> getResponseMappingList() {
        return responseMappingList;
    }

    public void setResponseMappingList(List<ResponseMapping> responseMappingList) {
        this.responseMappingList = responseMappingList;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, String> getRequestPayloads() {
        return requestPayloads;
    }

    public void setRequestPayloads(Map<String, String> requestPayloads) {
        this.requestPayloads = requestPayloads;
    }

    public Map<String, String[]> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String[]> queryParams) {
        this.queryParams = queryParams;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
