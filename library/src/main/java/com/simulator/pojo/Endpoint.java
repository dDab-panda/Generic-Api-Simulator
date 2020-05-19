
package com.simulator.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Request",
    "ResponseMapping",
    "customHandler"
})
public class Endpoint {

    @JsonProperty("Request")
    private Request request;
    @JsonProperty("ResponseMapping")
    private String responseMapping;
    @JsonProperty("customHandler")
    private String customHandler;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Request")
    public Request getRequest() {
        return request;
    }

    @JsonProperty("Request")
    public void setRequest(Request request) {
        this.request = request;
    }

    public Endpoint withRequest(Request request) {
        this.request = request;
        return this;
    }

    @JsonProperty("ResponseMapping")
    public String getResponseMapping() {
        return responseMapping;
    }

    @JsonProperty("ResponseMapping")
    public void setResponseMapping(String responseMapping) {
        this.responseMapping = responseMapping;
    }

    public Endpoint withResponseMapping(String responseMapping) {
        this.responseMapping = responseMapping;
        return this;
    }

    @JsonProperty("customHandler")
    public String getCustomHandler() {
        return customHandler;
    }

    @JsonProperty("customHandler")
    public void setCustomHandler(String customHandler) {
        this.customHandler = customHandler;
    }

    public Endpoint withCustomHandler(String customHandler) {
        this.customHandler = customHandler;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Endpoint withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(request).append(responseMapping).append(customHandler).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Endpoint) == false) {
            return false;
        }
        Endpoint rhs = ((Endpoint) other);
        return new EqualsBuilder().append(request, rhs.request).append(responseMapping, rhs.responseMapping).append(customHandler, rhs.customHandler).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
