package com.gptool.chartgpt.pojo;

import java.util.ArrayList;

public class Table {

    //  Might make more sense to have the Table object be a list of entry objects

    //  A table object is just a list of terms which are options and info
    public ArrayList<Term> terms;

    // Tables need to be initialized with an empty constructor before operations
    public Table(){
    } 

    public Table(ArrayList<Term> terms){
        this.terms = terms;
    }

    public ArrayList<Term> getTerms() {
        return this.terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }


}
