
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
    "method",
    "url",
    "requestpayload",
    "requestheaders"
})
public class Request {

    @JsonProperty("method")
    private String method;
    @JsonProperty("url")
    private String url;
    @JsonProperty("requestpayload")
    private Requestpayload requestpayload;
    @JsonProperty("requestheaders")
    private Requestheaders requestheaders;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    public Request withMethod(String method) {
        this.method = method;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Request withUrl(String url) {
        this.url = url;
        return this;
    }

    @JsonProperty("requestpayload")
    public Requestpayload getRequestpayload() {
        return requestpayload;
    }

    @JsonProperty("requestpayload")
    public void setRequestpayload(Requestpayload requestpayload) {
        this.requestpayload = requestpayload;
    }

    public Request withRequestpayload(Requestpayload requestpayload) {
        this.requestpayload = requestpayload;
        return this;
    }

    @JsonProperty("requestheaders")
    public Requestheaders getRequestheaders() {
        return requestheaders;
    }

    @JsonProperty("requestheaders")
    public void setRequestheaders(Requestheaders requestheaders) {
        this.requestheaders = requestheaders;
    }

    public Request withRequestheaders(Requestheaders requestheaders) {
        this.requestheaders = requestheaders;
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

    public Request withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(method).append(url).append(requestpayload).append(requestheaders).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Request) == false) {
            return false;
        }
        Request rhs = ((Request) other);
        return new EqualsBuilder().append(method, rhs.method).append(url, rhs.url).append(requestpayload, rhs.requestpayload).append(requestheaders, rhs.requestheaders).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
