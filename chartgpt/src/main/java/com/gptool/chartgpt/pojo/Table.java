package com.gptool.chartgpt.pojo;

import java.util.List;

public class Table {

    private String name;
    private List<Entry> entryList;

    public Table(){};

    public Table(List<Entry> entrylist){
        this.entryList = entrylist;
    };

    public List<Entry> getEntryList() {
        return this.entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}