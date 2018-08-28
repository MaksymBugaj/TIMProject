package com.maksy.Entity;

import java.util.Date;

public class Appointment {

    private int id;
    private int patientID;
    private int doctorID;
    private Date date;

    public Appointment(int id, int pateintID, int doctorID, Date date) {
        this.id = id;
        this.patientID = pateintID;
        this.doctorID = doctorID;
        this.date = date;
    }

    public Appointment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int pateintID) {
        this.patientID = pateintID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
