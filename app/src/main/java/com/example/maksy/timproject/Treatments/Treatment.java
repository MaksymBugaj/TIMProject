package com.example.maksy.timproject.Treatments;

public class Treatment {

    private String teatmentId;
    private String doctorName;
    private String doctorSpecialization;
    private String treatmentName;
    private String date;
    private String patientEmail;


    public Treatment() {
    }

    public Treatment(String teatmentId, String doctorName, String doctorSpecialization, String treatmentName, String date, String patientEmail) {
        this.teatmentId = teatmentId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.treatmentName = treatmentName;
        this.date = date;
        this.patientEmail = patientEmail;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
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
