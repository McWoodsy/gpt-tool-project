package com.gptool.chartgpt.repository;

import org.springframework.stereotype.Repository;
import com.gptool.chartgpt.pojo.Table;

@Repository
public class TableRepoImpl implements TableRepository {

     
//  why would we ever get by id? maybe need a mapping between id and a name/title
    @Override
    public Table getTable(String id) {
        return new Table();
        
    }
    
    @Override
    public void saveTable(Table table) {

    }
    }

