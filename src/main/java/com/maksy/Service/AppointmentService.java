package com.maksy.Service;

import com.maksy.Dao.AppointmentDao;
import com.maksy.Entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    public Collection<Appointment> getAllAppointments(){
        return appointmentDao.getAllAppointments();
    }

    public Appointment getAppointmentById(String id){
        return this.appointmentDao.getAppointmentById(id);
    }

    public void removeAppointmentById(int id) {
        this.appointmentDao.removeAppointmentById(id);
    }

    public void updateAppointment(Appointment appointment){
        this.appointmentDao.updateAppointment(appointment);
    }

    public void insertAppointment(Appointment appointment) {
        this.appointmentDao.insertAppointment(appointment);
    }
}