package com.simulator.pojo.response.mapping;


import java.util.List;

public final class ResponseMapping {
    private List<MagicData> magicData;
    private String response;

    public List<MagicData> getMagicData() {
        return magicData;
    }

    public void setMagicData(List<MagicData> magicData) {
        this.magicData = magicData;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}