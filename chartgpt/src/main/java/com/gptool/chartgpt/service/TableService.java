package com.gptool.chartgpt.service;

import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.service.utilities.Formatter;

public interface TableService {




    Table getTable(String id);
    void saveTable(Table table);
    void renderTable(Table table);
    String parse(Object object);
    //  Requires casting
    Object parseToObject(String src, Formatter.OutputObjectType outputObject);     
}
