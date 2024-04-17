package com.gptool.chartgpt.repository;

import org.springframework.stereotype.Repository;
import org.json.simple.JSONObject;

import com.gptool.chartgpt.pojo.Table;

@Repository
public class TableRepoImpl implements TableRepository {

     
//  why would we ever get by id? maybe need a mapping between id and a name/title
    @Override
    public Table getTable(String id) {
        //  Function which 
        return new Table();
        
    }
    
    @Override
    public void saveTable(Table table) {
        //  Function to write to a JSON file, for ease of use we'll do one file per table. it will cut the code length drastically
    }
}
