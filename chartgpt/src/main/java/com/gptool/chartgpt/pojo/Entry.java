package com.gptool.chartgpt.pojo;
import java.util.List;


public class Entry {
  
    //  This field is the same as on a Table object
    private List<String> topics;
    
    private List<String> information;

    //  Each Entry has just one option
    private String option;

    public Entry(){};

    public Entry(String option, List<String> topics, List<String> information) {
        this.option = option;
        this.topics = topics;
        this.information = information;
    };

    public List<String> getTopics() {
        return this.topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getInformation() {
        return this.information;
    }

    public void setInformation(List<String> information) {
        this.information = information;
    }

    public String getOptions() {
        return this.option;
    }

    public void setOptions(String option) {
        this.option = option;
    }
}
