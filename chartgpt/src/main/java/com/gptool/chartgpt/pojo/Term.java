package com.gptool.chartgpt.pojo;

import java.util.ArrayList;

public class Term {

    //  Each Term in a table has identically indexed options and information for that term corresponding to that option
    ArrayList<String> options;
    ArrayList<String> information; 
    


    public ArrayList<String> getOptions() {
        return this.options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<String> getInformation() {
        return this.information;
    }

    public void setInformation(ArrayList<String> information) {
        this.information = information;
    }

    public Term(ArrayList<String> options, ArrayList<String> information) {
        this.options = options;
        this.information = information;
    }
}
