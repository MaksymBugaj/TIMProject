package com.example.maksy.timproject.Entity;

public class Treatment {

    private String name;
    private String docName;
    private String desc;

    public Treatment() {
    }

    public Treatment(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Treatment(String name, String docName, String desc) {
        this.name = name;
        this.docName = docName;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
