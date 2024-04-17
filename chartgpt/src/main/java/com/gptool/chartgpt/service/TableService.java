package com.gptool.chartgpt.service;

import com.gptool.chartgpt.pojo.Table;

public interface TableService {

    Table getTable(String id);
    void saveTable(Table table);
    
}
