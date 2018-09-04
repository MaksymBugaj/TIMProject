package com.example.maksy.timproject.Calendar;

public class Visit {

    private String doctorName;
    private String doctorSpecialization;

    public Visit() {
    }

    public Visit(String doctorName, String doctorSpecialization) {
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
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
}
