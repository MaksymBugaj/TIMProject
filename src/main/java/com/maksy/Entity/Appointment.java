package com.maksy.Entity;

import java.util.Date;

public class Appointment {

    private String id;
    private String name;
    private String patientId;
    private String doctorId;
    private String doctorName;
    private String doctorSurname;
    private String date;

    public Appointment() {
    }

    public Appointment(String id, String name, String patientId, String doctorId, String doctorName, String doctorSurname, String date) {
        this.id = id;
        this.name = name;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSurname = doctorSurname;
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientId;
    }

    public void setPatientID(String patientID) {
        this.patientId = patientID;
    }

    public String getDoctorID() {
        return doctorId;
    }

    public void setDoctorID(String doctorID) {
        this.doctorId = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
