
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
    "status",
    "responsepayload",
    "responseheaders"
})
public class Response {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("responsepayload")
    private Responsepayload responsepayload;
    @JsonProperty("responseheaders")
    private Responseheaders responseheaders;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Response withStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonProperty("responsepayload")
    public Responsepayload getResponsepayload() {
        return responsepayload;
    }

    @JsonProperty("responsepayload")
    public void setResponsepayload(Responsepayload responsepayload) {
        this.responsepayload = responsepayload;
    }

    public Response withResponsepayload(Responsepayload responsepayload) {
        this.responsepayload = responsepayload;
        return this;
    }

    @JsonProperty("responseheaders")
    public Responseheaders getResponseheaders() {
        return responseheaders;
    }

    @JsonProperty("responseheaders")
    public void setResponseheaders(Responseheaders responseheaders) {
        this.responseheaders = responseheaders;
    }

    public Response withResponseheaders(Responseheaders responseheaders) {
        this.responseheaders = responseheaders;
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

    public Response withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(responsepayload).append(responseheaders).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Response) == false) {
            return false;
        }
        Response rhs = ((Response) other);
        return new EqualsBuilder().append(status, rhs.status).append(responsepayload, rhs.responsepayload).append(responseheaders, rhs.responseheaders).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
