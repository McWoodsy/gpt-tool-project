package com.gptool.chartgpt.repository;

import com.gptool.chartgpt.pojo.Table;

public interface TableRepository {

    Table getTable(String id);
    void saveTable(String table);
    
}
