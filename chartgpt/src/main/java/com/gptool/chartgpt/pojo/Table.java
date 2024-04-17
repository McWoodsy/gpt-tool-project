package com.gptool.chartgpt.pojo;

import java.util.ArrayList;
import java.util.UUID;

public class Table {
    
    //  A table object is just a list of terms which are options and info
    public String id;
    public ArrayList<Term> terms;

    // Tables need to be initialized with an empty constructor before operations
    public Table(){
        this.id = UUID.randomUUID().toString();
    } 

    public Table(String id, ArrayList<Term> terms){
        this.id = id;
        this.terms = terms;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Term> getTerms() {
        return this.terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }


}
