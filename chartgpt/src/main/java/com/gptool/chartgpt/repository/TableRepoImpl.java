package com.gptool.chartgpt.repository;

import org.springframework.stereotype.Repository;

import com.gptool.chartgpt.pojo.Table;

@Repository
public class TableRepoImpl implements TableRepository {

     

    @Override
    public Table getTable(String id) {
        //  This "new" Table object is used to render via Thymeleaf of javascript (it's newness or new ID is irrelevant)
        return new Table();
        
    }
    
    @Override
    public void saveTable(Table table) {

    }
}
