package com.gptool.chartgpt.service;

import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.repository.TableRepository;
import com.gptool.chartgpt.service.serviceutil.Formatter;
import com.gptool.chartgpt.service.serviceutil.JSONutil;

@Service
@Primary
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    JSONutil JSONutil;
    
    @Override
    public Table getTable(String id) {
        return tableRepository.getTable(id);
    }

    @Override
    public void saveTable(Table table) throws JsonProcessingException {
        String tableString = JSONutil.objectMapper.writeValueAsString(tableToJson(table));
        tableRepository.saveTable(tableString);
    }

    @Override
    public void renderTable(Table table){
    }

    //  Parses objects to string
    @Override
    public String parse(Object object) {
        if (object instanceof JsonNode) {
        return JSONutil.parseToString((JsonNode)object);
    }
        else {
            return "Unknown type";
        }
    }

    @Override
    public Object parseToObject(String src, Formatter.OutputObjectType outputObjectType) {
        if (outputObjectType == Formatter.OutputObjectType.JsonNode) {
            //  Exception is caught inside the function         
            return JSONutil.parse(src);
        } 
        else if (outputObjectType == Formatter.OutputObjectType.Entry) {
            JsonNode jsonNode = (JsonNode) JSONutil.parse(src);
            try{
                Entry entryObject = JSONutil.objectMapper.treeToValue(jsonNode, Entry.class);
                return entryObject;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new Object();
            } 
        }
        return new Object();

    }

    public Table jsonToTable(String json) throws JsonMappingException, JsonProcessingException {
        return JSONutil.jsonToTable(json);
    }

    //  Not needed
    public JsonNode tableToJson(Table table) throws JsonProcessingException {
        String tableString = JSONutil.objectMapper.writeValueAsString(table);
        JsonNode jsonNodeTable = (JsonNode)JSONutil.parse(tableString);
        return jsonNodeTable;
    }

}



