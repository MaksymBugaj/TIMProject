package com.maksy.Dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.maksy.Entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AppointmentDao {

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private static Map<String, Appointment> appointments = new HashMap<String,Appointment>();

    /*static {
        appointments = new HashMap<Integer, Appointment>(){
            {
                put(1, new Appointment(1,3,1,new Date()));
                put(2, new Appointment(2,4,2,new Date()));
                put(3, new Appointment(3,3,5,new Date()));

            }
        };
    }*/

    private void initAppointments(){

    }
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
        String appointmentId = databaseReference.push().getKey();
        this.appointments.put(appointment.getId(),appointment);
    }
}