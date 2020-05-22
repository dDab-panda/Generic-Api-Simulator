package com.simulator.pojo.config;


import java.util.Objects;

public final class ConfigKey {
    private String type;
    private String url;
    private String method;


    public ConfigKey(String type, String url, String method) {
        this.type = type;
        this.url = url;
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConfigKey configKey = (ConfigKey)o;
        return Objects.equals(type, configKey.type) &&
                Objects.equals(url, configKey.url) &&
                Objects.equals(method, configKey.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, url, method);
    }

    @Override
    public String toString() {
        return "ConfigKey{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}