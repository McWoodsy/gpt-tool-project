package com.gptool.chartgpt.pojo;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entry {

    //public Map<Option,OptionInfo> entryMap;
  
    @JsonProperty("topics")
    private List<String> topics;
    
    @JsonProperty("information")
    private List<List<String>> information;

    @JsonProperty("options")
    private List<String> options;

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

    public List<String> getOptions() {
        return this.options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
