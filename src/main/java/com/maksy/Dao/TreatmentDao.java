package com.maksy.Dao;

import com.maksy.Entity.Treatment;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TreatmentDao {

    private static Map<Integer, Treatment> treatments;

    public Collection<Treatment> getAllTreatments(){
        return this.treatments.values();
    }

    public Treatment getTreatmentById(int id){
        return this.treatments.get(id);
    }

    public void removeTreatmentById(int id) {
        this.treatments.remove(id);
    }

    public void updateTreatment(Treatment treatment){
        Treatment treatment1 = treatments.get(treatment.getId());
        treatment1.setName(treatment.getName());
        treatment1.setDoctorID(treatment.getDoctorID());
        treatments.put(treatment.getId(),treatment);
    }

    public void insertTreatment(Treatment treatment) {
        this.treatments.put(treatment.getId(),treatment);
    }
}