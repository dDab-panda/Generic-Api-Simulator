package com.simulator.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

public final class JsonToMapConvertorUtil {

    public static Map<String,String> convertJsonToMap(String jsonStr) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> resolvedMap = new LinkedHashMap<>();
        Map<String, Object> requestMap = mapper.readValue(jsonStr,
                new TypeReference<Map<String, Object>>() {});
        convertToMap(requestMap, resolvedMap, null);
        return resolvedMap;
    }

    private static void convertToMap(Map<String, Object> requestMap, Map<String, String> resolvedMap, String rootKey){
        rootKey = StringUtils.isEmpty(rootKey) ? "" : rootKey + ".";
        for (String key : requestMap.keySet()){
            Object val = requestMap.get(key);
            String extendedKey = rootKey + key;
            if(val instanceof Map){
                convertToMap((Map<String, Object> ) val, resolvedMap, extendedKey);
            } else if (val instanceof List){
                convertToMap((List <Object> ) val, resolvedMap, extendedKey);
            } else if (val instanceof String){
                resolvedMap.put(extendedKey, (String)val);
            } else {
                System.out.println(val);
            }
        }
    }

    private static void convertToMap(List<Object> requestLst, Map<String, String> resolvedMap, String rootKey){
        rootKey = StringUtils.isEmpty(rootKey) ? "" : rootKey;
        for(int i = 0; i < requestLst.size(); i++) {
            Object val = requestLst.get(i);
            String extendedKey = rootKey + i;
            if(val instanceof Map){
                convertToMap((Map<String, Object> ) val, resolvedMap, extendedKey);
            } else if (val instanceof List){
                convertToMap((List <Object> ) val, resolvedMap, extendedKey);
            } else if (val instanceof String){
                resolvedMap.put(extendedKey, (String)val);
            }
        }
    }


    public static void main(String[] args) throws Exception{
        /*String json = "{\n" +
                "   \"type\":\"header\",\n" +
                "   \"name\":\"accept\",\n" +
                "   \"value\":\"application/json\"\n" +
                "}";*/
        /*String json = "{\n" +
                "   \"type\":\"header\",\n" +
                "   \"name\":\"accept\",\n" +
                "   \"value\":\"application/json\",\n" +
                "   \"header\":{\n" +
                "      \"name\":\"test\",\n" +
                "      \"value\":\"test\"\n" +
                "   }\n" +
                "}";*/

        String json = "{\n" +
                "   \"type\":\"header\",\n" +
                "   \"name\":\"accept\",\n" +
                "   \"value\":\"application/json\",\n" +
                "   \"header\":{\n" +
                "      \"name\":\"test\",\n" +
                "      \"value\":\"test\"\n" +
                "   },\n" +
                "   \"simplearray\":[\n" +
                "      \"test\",\n" +
                "      \"test2\"\n" +
                "   ],\n" +
                "   \"array\":[\n" +
                "      {\n" +
                "         \"name\":\"test\",\n" +
                "         \"value\":\"test\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"name\":\"test2\",\n" +
                "         \"value\":\"test2\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        System.out.println("json -> " +  json);

        Map<String, String> resolvedMap = convertJsonToMap(json);

        System.out.println("parsed json --> " + resolvedMap);

    }
}