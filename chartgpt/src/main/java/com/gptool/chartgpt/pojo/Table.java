package com.gptool.chartgpt.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Table {

    @JsonProperty("topics")
    private List<String> topics;
    
    @JsonProperty("information")
    private List<List<String>> information;

    @JsonProperty("options")
    private List<String> option;

    public Table(){};

    public List<String> getTopics() {
        return this.topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<List<String>> getInformation() {
        return this.information;
    }

    public void setInformation(List<List<String>> information) {
        this.information = information;
    }

    public List<String> getOption() {
        return this.option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }



}