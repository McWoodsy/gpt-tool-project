package com.gptool.chartgpt.service.utilities;

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
