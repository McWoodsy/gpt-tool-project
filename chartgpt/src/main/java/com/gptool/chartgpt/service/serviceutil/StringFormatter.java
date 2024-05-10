package com.gptool.chartgpt.service.serviceutil;

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
    public static String urlFormatter(String src) {
        String formattedUrl = src.replaceAll(" ", "+");
        return formattedUrl;
    };

    public static String removeBackslash(String src) {
        char[] charArray = src.toCharArray();
        StringBuilder formattedString = new StringBuilder();
        for (char character : charArray) {
            if (character != '\\') {
                formattedString.append(character);
            }
        }
        return formattedString.toString();
    }
}
