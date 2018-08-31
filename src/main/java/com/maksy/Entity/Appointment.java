package com.maksy.Entity;

import java.util.Date;

public class Appointment {

    private String id;
    private String patientID;
    private String doctorID;
    private String date;

    public Appointment(String id, String patientID, String doctorID, String date) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
