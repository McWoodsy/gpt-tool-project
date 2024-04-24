package com.gptool.chartgpt.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import com.gptool.chartgpt.pojo.Table;

@Repository
@Primary
public class TableRepoImpl implements TableRepository {

    @Override
    public Table getTable(String id) {
        return new Table();
        
    }
    
    @Override
    public void saveTable(Table table) {
        System.out.println("TABLE SAVED");
    }
}

