package com.gptool.chartgpt.service.serviceutil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gptool.chartgpt.pojo.Entry;
import com.gptool.chartgpt.pojo.Table;

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

    public Table jsonToTable(String json) throws JsonMappingException, JsonProcessingException {
        Table table = objectMapper.readValue(json, Table.class);
        return table;
    }

    //  For now, to access Entrys we need to create a Table first
    public List<Entry> entryListMapper(Table table ) throws JsonProcessingException{
        List<Entry> entryList = new ArrayList<>();
        String tableString = objectMapper.writeValueAsString(table);
        JsonNode tableJsonNode = (JsonNode)parse(tableString);
        // Getting just the list of options
        JsonNode optionListJsonNode = tableJsonNode.get("options");
        String optionListString = (String) parseToString(optionListJsonNode);
        List<String> optionList = objectMapper.readValue(optionListString,new TypeReference<List<String>>(){});

        //  Getting just the list of topics
        JsonNode topicListJsonNode = tableJsonNode.get("topics");
        String topicListString = (String) parseToString(topicListJsonNode);
        List<String> topicList = objectMapper.readValue(topicListString,new TypeReference<List<String>>(){});

        //  Getting the list of information lists
        JsonNode informationListJsonNode = tableJsonNode.get("topics");
        String informationListString = (String) parseToString(informationListJsonNode);
        List<List<String>> informationList = objectMapper.readValue(informationListString,new TypeReference<List<List<String>>>(){});

        for (int index = 0; index < optionList.size(); index++) {
            List<String> optionInfo = new ArrayList<>();
            //  For indexed option get the indexed item in each list
            for (List<String> information : informationList) {
                optionInfo.add(information.get(index));
            }
            Entry entry = new Entry(optionList.get(index), topicList, optionInfo);
            entryList.add(entry);
        }
        return entryList;
    }


    

    }

