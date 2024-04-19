package com.gptool.chartgpt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.pojo.Term;
import com.gptool.chartgpt.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ObjectMapper objectMapper;
    

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
    @Override
    public Table parseTableFromJSON(JSONObject json) {
        return new Table();
}


}
