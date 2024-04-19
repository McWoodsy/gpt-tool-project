package com.gptool.chartgpt.repository;

import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;

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
        // Save JSON to a file
        // try (FileWriter file = new FileWriter("JSON\\orders.json")) {
        //     file.write(table.toJSONString());
        //     System.out.println("\nOrder data saved to order.json\n");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } 
    }
    }

