package com.gptool.chartgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gptool.chartgpt.pojo.Table;
import com.gptool.chartgpt.service.serviceutil.Formatter;

public interface TableService {




    Table getTable(String id);
    void saveTable(Table table)throws JsonProcessingException;
    void renderTable(Table table);
    String parse(Object object);
    //  Requires casting
    Object parseToObject(String src, Formatter.OutputObjectType outputObject); 
    Table jsonToTable(String json) throws JsonMappingException, JsonProcessingException;
       
}
