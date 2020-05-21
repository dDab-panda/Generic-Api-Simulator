package com.simulator.pojo.response.mapping;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

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
    private JSONObject response;

    @JsonProperty("magicdata")
    public List<MagicData> getMagicdata() {
        return magicdata;
    }

    @JsonProperty("magicdata")
    public void setMagicdata(List<MagicData> magicdata) {
        this.magicdata = magicdata;
    }

    @JsonProperty("response")
    public JSONObject getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public static List<ResponseMapping> getMappingFromMagicFile(File magicFile){
        List<ResponseMapping> responseMappingList=null;
        ObjectMapper mapper = new ObjectMapper();

        try {
            responseMappingList = mapper.readValue(magicFile, new TypeReference<List<ResponseMapping>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMappingList;
    }
}
