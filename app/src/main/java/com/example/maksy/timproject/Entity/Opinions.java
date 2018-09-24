package com.example.maksy.timproject.Entity;

public class Opinions {
    private String doctorName;
    private String patientName;
    private String opinion;
    private String rating;

    public Opinions() {
    }

    public Opinions(String doctorName, String patientName, String opinion, String rating) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.opinion = opinion;
        this.rating = rating;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
