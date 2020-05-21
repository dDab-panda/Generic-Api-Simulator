package com.simulator.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class Helper {

    public static Map<String,String> parseJSONObjectToMap(JSONObject jsonObject) throws JSONException{
        Map<String, String> mapData = new HashMap<String, String>(); //TODO: Map<String, Object> mapData = new HashMap<String, Object>()
        Iterator<String> keysItr = jsonObject.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = jsonObject.get(key);

            if(value instanceof JSONArray) {
                value = parseJSONArrayToList((JSONArray) value);
            }else if(value instanceof JSONObject) {
                value = parseJSONObjectToMap((JSONObject) value);
            }
            //TODO: Refactor this logic to get the response as List<String, String>, Current logic is not validated.
            mapData.put(key, value.toString());
        }
        return mapData;
    }

    public static List<Object> parseJSONArrayToList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = parseJSONArrayToList((JSONArray) value);
            }else if(value instanceof JSONObject) {
                value = parseJSONObjectToMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
