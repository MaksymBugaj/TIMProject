package com.maksy.Controller;

import com.maksy.Entity.Appointment;
import com.maksy.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Collection<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Appointment getAppointmentById(@PathVariable("id") int id){
        return appointmentService.getAppointmentById(id);
    }

    //////////////////////////////////////////////////
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public void deleteAppointmentById(@PathVariable("id") int id){
        appointmentService.removeAppointmentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public void updateAppointment(@RequestBody Appointment appointment){
        appointmentService.updateAppointment(appointment);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public void insertAppointment(@RequestBody Appointment appointment){
        appointmentService.insertAppointment(appointment);
    }
}
