package com.maksy.Dao;

import com.maksy.Entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AppointmentDao {

    private static Map<Integer, Appointment> appointments;

    public Collection<Appointment> getAllAppointments(){
        return this.appointments.values();
    }

    public Appointment getAppointmentById(int id){
        return this.appointments.get(id);
    }

    public void removeAppointmentById(int id) {
        this.appointments.remove(id);
    }

    public void updateAppointment(Appointment appointment){
        Appointment appointment1 = appointments.get(appointment.getId());
        appointment1.setPatientID(appointment.getPatientID());
        appointment1.setDoctorID(appointment.getDoctorID());
        appointment1.setDate(appointment.getDate());
        appointments.put(appointment.getId(),appointment);
    }

    public void insertAppointment(Appointment appointment) {
        this.appointments.put(appointment.getId(),appointment);
    }
}