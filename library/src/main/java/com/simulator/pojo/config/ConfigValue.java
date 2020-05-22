package com.simulator.pojo.config;


import com.simulator.pojo.Application;
import com.simulator.pojo.Endpoint;

public final class ConfigValue {
    private Application application;
    private Endpoint endpoint;

    public ConfigValue(Application application, Endpoint endpoint) {
        this.application = application;
        this.endpoint = endpoint;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

}