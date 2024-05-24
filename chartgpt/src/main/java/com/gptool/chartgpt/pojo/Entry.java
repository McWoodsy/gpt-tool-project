package com.gptool.chartgpt.pojo;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {

    //public Map<Option,OptionInfo> entryMap;
  
    @JsonProperty("topics")
    private List<String> topics;
    
    @JsonProperty("information")
    private List<String> information;

    //  Each Entry is just one option
    //@JsonProperty("options")
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
