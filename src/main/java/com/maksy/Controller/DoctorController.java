package com.maksy.Controller;

import com.maksy.Entity.Doctor;
import com.maksy.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/Doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Collection<Doctor> getAllDoctors() {
        return DoctorService.getAllDoctors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Doctor getDoctorById(@PathVariable("id") int id){
        return DoctorService.getDoctorById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteDoctorById(@PathVariable("id") int id){
        DoctorService.removeDoctorById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDoctor(@RequestBody Doctor doctor){
        DoctorService.updateDoctor(doctor);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertDoctor(@RequestBody Doctor doctor){
        DoctorService.insertDoctor(doctor);
    }
}
