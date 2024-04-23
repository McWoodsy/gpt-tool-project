package com.gptool.chartgpt.pojo;

public class Information {

    private String value;
    private Topic topic;
    private Option option;

    public Information() {
    }

    public Information(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Option getOption() {
        return this.option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
