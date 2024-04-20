package com.gptool.chartgpt.service.utilities;

public class StringFormatter implements Formatter{

    //Logic for formatting strings
    @Override
    public String parseToString(Object object ){
        return "";
    };

    @Override 
    public Object parse(String src) {
        return "No implementation";
    };

    // For when new html is set up
    public String urlFormatter(String src) {
        String formattedUrl = src.replaceAll(" ", "+");
        return formattedUrl;
    };
}
