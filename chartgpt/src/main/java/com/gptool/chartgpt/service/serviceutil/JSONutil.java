package com.gptool.chartgpt.service.serviceutil;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JSONutil implements Formatter {

    public ObjectMapper objectMapper = getDefaultObjectMapper();

    private ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        return defaultObjectMapper;
    }

    @Override
    public Object parse(String src) {
        try{
            return objectMapper.readTree(src);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        } 
}


    @Override
    public String parseToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            // Handle the exception (e.g., log error)
            e.printStackTrace();
            return ""; // Return a default value
        }
    }

    //  If i go for the less abstracted pojos route i can have specific parsers for table and entries
}