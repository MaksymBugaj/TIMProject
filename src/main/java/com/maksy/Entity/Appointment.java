package com.maksy.Entity;

public class Appointment {

    private int id;
    private int pateintID;
    private int DoctorID;
    private Date date;

    public Appointment(int id, int pateintID, int doctorID, Date date) {
        this.id = id;
        this.pateintID = pateintID;
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

    public int getPateintID() {
        return pateintID;
    }

    public void setPateintID(int pateintID) {
        this.pateintID = pateintID;
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
