package com.maksy.Dao;

import com.google.firebase.database.*;
import com.maksy.Entity.Appointment;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AppointmentDao {

    private DatabaseReference databaseReference,userDatabaseReference;
    private FirebaseDatabase firebaseDatabase;
    private static Map<String, Appointment> appointments = new HashMap<>();
    private String doctorName, doctorSurname;
    /*static {
        appointments = new HashMap<Integer, Appointment>(){
            {
                put(1, new Appointment(1,3,1,new Date()));
                put(2, new Appointment(2,4,2,new Date()));
                put(3, new Appointment(3,3,5,new Date()));

            }
        };
    }*/

    private void initAppointments() {
        firebaseDatabase = FirebaseDatabase.getInstance();

        userDatabaseReference = firebaseDatabase.getReference("users");
//todo equal to has to show special doctor
        Query query1 = userDatabaseReference.orderByChild("type").equalTo("1");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String mDoctorName = (String) dataSnapshot1.child("firstname").getValue();
                    String mDoctorSurname = (String) dataSnapshot1.child("surname").getValue();
                    setDoctorName(mDoctorName);
                    setDoctorSurname(mDoctorSurname);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference = firebaseDatabase.getReference("appointments");

        Query query = databaseReference.orderByChild("email");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String id = (String) dataSnapshot1.child("id").getValue();
                    String name = (String) dataSnapshot1.child("name").getValue();
                    String patientId = (String) dataSnapshot1.child("patientId").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorId").getValue();
                    String date = (String) dataSnapshot1.child("date").getValue();
                    Appointment appointment = new Appointment(id, name, patientId, doctorId,getDoctorName(),getDoctorSurname(), date);
                    appointments.put(appointment.getId(),appointment);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Collection<Appointment> getAllAppointments() {
        initAppointments();
        return this.appointments.values();
    }

    public Appointment getAppointmentById(String id) {

        return this.appointments.get(id);
    }

    public void removeAppointmentById(int id) {
        this.appointments.remove(id);
    }

    public void updateAppointment(Appointment appointment) {
        /*Appointment appointment1 = appointments.get(appointment.getId());
        appointment1.setPatientID(appointment.getPatientID());
        appointment1.setDoctorID(appointment.getDoctorID());
        appointment1.setDate(appointment.getDate());
        appointments.put(appointment.getId(), appointment);*/
    }

    public void insertAppointment(Appointment appointment) {
        initAppointments();
        String appointmentId = databaseReference.push().getKey();
        Appointment appointment1 = new Appointment(appointmentId,appointment.getName(),appointment.getPatientID(),appointment.getDoctorID(),appointment.getDoctorName(),appointment.getDoctorSurname(),appointment.getDate());
        databaseReference.child(appointmentId).setValue(appointment1);
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }
}