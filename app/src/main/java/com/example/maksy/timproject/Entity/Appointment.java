package com.example.maksy.timproject.Entity;

import java.util.Date;

public class Appointment {

    private String treatName;
    private String time;
    private String doctorEmail;
    private Long date;
    private String patientEmail;
    private int flag;

    public Appointment() {
    }

    public Appointment(String treatName, String time, String doctorEmail, Long date, String patientEmail, int flag) {
        this.treatName = treatName;
        this.time = time;
        this.doctorEmail = doctorEmail;
        this.date = date;
        this.patientEmail = patientEmail;
        this.flag = flag;
    }

    public Appointment(String treatName, String doctorEmail, Long date) {
        this.treatName = treatName;
        this.doctorEmail = doctorEmail;
        this.date = date;
    }

    public String getTreatName() {
        return treatName;
    }

    public void setTreatName(String treatName) {
        this.treatName = treatName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
