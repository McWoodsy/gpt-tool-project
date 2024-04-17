package com.gptool.chartgpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

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
    };
    
}
