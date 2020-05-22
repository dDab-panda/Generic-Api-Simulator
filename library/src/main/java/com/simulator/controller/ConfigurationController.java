package com.simulator.controller;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.simulator.application.config.ConfigurationLoader;
import com.simulator.handler.RequestHandlerFactory;
import com.simulator.pojo.Config;
import com.simulator.pojo.config.ConfigKey;
import com.simulator.pojo.config.ConfigValue;
import com.simulator.pojo.response.mapping.ResponseMapping;
import com.simulator.service.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ConfigurationController {

    static final Logger logger = LoggerFactory.getLogger(EndpointController.class);

    @Autowired
    public ConfigurationLoader configfile;

    @Autowired
    private RequestContext requestContext;

    @Autowired
    private RequestHandlerFactory requestHandlerFactory;


    @RequestMapping("/config")
    public @ResponseBody
    Config sendConfig() throws ProcessingException, IOException {
        logger.trace("Configuration file viewed.");
        return configfile.getConfig();
    }

    @RequestMapping("/configmap")
    public @ResponseBody
    Map<ConfigKey, ConfigValue> sendConfigMap() throws ProcessingException, IOException {
        logger.trace("Configuration map accessed.");
        return configfile.getConfigMap();
    }
    @RequestMapping("/responsemapping")
    public @ResponseBody
    List<ResponseMapping> sendResponseMapping() throws ProcessingException, IOException {
        logger.trace("Response Mapping sent.");
        return configfile.getResponseMapping();
    }

}