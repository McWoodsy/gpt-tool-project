package com.gptool.chartgpt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //  ???
    }

    //  Parses objects to string
    @Override
    public String parse(Object object) {
        if (object instanceof JsonNode) {
        return JSONutil.parseToString((JsonNode)object);
    }
        // else if (object instanceof Table) {
        //     //  convert table to JSON then JSON to string
        // }
        // else if (object instanceof Entry) {
        //     //  convert table to JSON then JSON to string
        // }
        // else if (object instanceof Information) {
        //     //  convert table to JSON then JSON to string
        // }
        // else if (object instanceof Topic) {
        //     //  convert table to JSON then JSON to string
        // }
        // else if (object instanceof Option) {
        //     //  convert table to JSON then JSON to string
        // }
        else {
            return "";
        }
    }

    // can still use this by plugging the class enum into the treeToValue function
    @Override
    public Object parseToObject(String src, Formatter.OutputObjectType outputObject) {
        if (outputObject == Formatter.OutputObjectType.JsonNode) {
            return JSONutil.parse(src);
        } 
        return JSONutil.parse(src);

        else if (outputObject == Formatter.OutputObjectType.Entry) {
            return 
        }
    }

}

