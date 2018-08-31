package com.maksy.Entity;

public class Treatment {

    private String id;
    private String name;
    private String doctorId;

    public Treatment(String id, String name, String doctorId) {
        this.id = id;
        this.name = name;
        this.doctorId = doctorId;
    }

    public Treatment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}
