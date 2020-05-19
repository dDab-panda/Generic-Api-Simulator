package com.simulator.application.config;

public class RequestContext {
	EndpointConf conf;
    Map<String,String> requestHeaders;
    Map<String,String> requestPayloads;
    Map<String,String> queryParams;
    Map<MagicData, Response> magicDataResponse;
}
