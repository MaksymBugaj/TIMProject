package com.example.maksy.timproject.Appointments;

public class Appointment {

    private String appointmentId;
    private String doctorName;
    private String doctorSpecialization;
    private String date;
    private String patientEmail;

    public Appointment(String appointmentId, String doctorName, String doctorSpecialization, String date, String patientEmail) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.date = date;
        this.patientEmail = patientEmail;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Appointment() {
    }

    public Appointment(String doctorName, String doctorSpecialization, String date) {
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }
}
