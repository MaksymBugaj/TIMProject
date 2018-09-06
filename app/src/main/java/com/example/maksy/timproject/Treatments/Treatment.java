package com.example.maksy.timproject.Treatments;

public class Treatment {

    private String doctorName;
    private String doctorSpecialization;
    private String teatmentId;
    private String date;

    public Treatment() {
    }

    public Treatment(String doctorName, String doctorSpecialization, String teatmentId, String date) {
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.teatmentId = teatmentId;
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public String getTeatmentId() {
        return teatmentId;
    }

    public void setTeatmentId(String teatmentId) {
        this.teatmentId = teatmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
