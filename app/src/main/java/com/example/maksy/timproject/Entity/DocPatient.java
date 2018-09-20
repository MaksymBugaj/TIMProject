package com.example.maksy.timproject.Entity;

public class DocPatient {

    private String key;
    private String treatName;
    private String patientName;
    private String patientEmail;
    private String date;

    public DocPatient() {
    }

    public DocPatient(String key, String treatName, String patientName, String patientEmail, String date) {
        this.key = key;
        this.treatName = treatName;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.date = date;
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
