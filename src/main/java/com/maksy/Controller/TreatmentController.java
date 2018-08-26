package com.maksy.Controller;

import com.maksy.Entity.Treatment;
import com.maksy.Service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/Treatments")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Collection<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
    public Treatment getTreatmentById(@PathVariable("id") int id){
        return treatmentService.getTreatmentById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteTreatmentById(@PathVariable("id") int id){
        treatmentService.removeTreatmentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTreatment(@RequestBody Treatment treatment){
        treatmentService.updateTreatment(treatment);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertTreatment(@RequestBody Treatment treatment){
        TreatmentService.insertTreatment(treatment);
    }
}
