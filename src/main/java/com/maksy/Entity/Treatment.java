package com.maksy.Entity;

public class Treatment {

    private int id;
    private String name;
    private int doctorID;

    public Treatment(int id, String name, int doctorID) {
        this.id = id;
        this.name = name;
        this.doctorID = doctorID;
    }

    public Treatment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
}
