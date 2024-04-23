package com.gptool.chartgpt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.gptool.chartgpt.pojo.Information;
import com.gptool.chartgpt.pojo.Option;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.pojo.Term;
import com.gptool.chartgpt.pojo.Topic;
import com.gptool.chartgpt.repository.TableRepository;
import com.gptool.chartgpt.service.utilities.Formatter;
import com.gptool.chartgpt.service.utilities.JSONutil;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private JSONutil JSONutil;
    
    @Override
    public Table getTable(String id) {
        return tableRepository.getTable(id);
    }

    @Override
    public void saveTable(Table table) {
        tableRepository.saveTable(table);
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

    }}



