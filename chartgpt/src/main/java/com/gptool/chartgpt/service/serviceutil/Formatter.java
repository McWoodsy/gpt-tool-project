package com.gptool.chartgpt.service.serviceutil;

public interface Formatter {


    String parseToString(Object object);
    Object parse(String src);
    //  


    
    enum OutputObjectType {
        JsonNode,
        Table,
        Entry,
        Option,
        Topic,
        Information;
    }
    
}
