package com.gptool.chartgpt.service;

import org.json.simple.JSONObject;

import com.gptool.chartgpt.pojo.Table;

public interface TableService {

    Table getTable(String id);
    void saveTable(Table table);
    void renderTable(Table table);
    Table parseTableFromJSON(JSONObject json);
    

    
}
