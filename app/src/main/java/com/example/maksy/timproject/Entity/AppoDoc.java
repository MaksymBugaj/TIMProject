package com.example.maksy.timproject.Entity;

import java.util.Date;

public class AppoDoc {

    private String key;
    private String treatName;
    private String doctorName;
    private String doctorEmail;
    private String date;

    public AppoDoc(String key,String treatName, String doctorName, String doctorEmail, String date) {
        this.treatName = treatName;
        this.doctorName = doctorName;
        this.doctorEmail = doctorEmail;
        this.date = date;
        this.key=key;
    }



    public AppoDoc() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTreatName() {
        return treatName;
    }

    public void setTreatName(String treatName) {
        this.treatName = treatName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
