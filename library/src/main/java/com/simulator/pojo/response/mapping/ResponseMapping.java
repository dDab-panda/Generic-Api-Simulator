package com.simulator.pojo.response.mapping;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.File;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "magicdata",
        "response"
})
public class ResponseMapping {

    @JsonProperty("magicdata")
    private List<MagicData> magicdata = null;

    @JsonProperty("response")
    private MagicResponse response;

    @JsonProperty("magicdata")
    public List<MagicData> getMagicdata() {
        return magicdata;
    }

    @JsonProperty("magicdata")
    public void setMagicdata(List<MagicData> magicdata) {
        this.magicdata = magicdata;
    }

    @JsonProperty("response")
    public MagicResponse getMagicResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(MagicResponse response) {
        this.response = response;
    }
}
