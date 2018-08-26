package com.maksy.Service;

import com.maksy.Dao.TreatmentDao;
import com.maksy.Entity.Treatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentDao treatmentDao;

    public Collection<Treatment> getAllTreatments(){
        return treatmentDao.getAllTreatments();
    }

    public Treatment getTreatmentById(int id){
        return this.treatmentDao.getTreatmentById(id);
    }

    public void removeTreatmentById(int id) {
        this.treatmentDao.removeTreatmentById(id);
    }

    public void updateTreatment(Treatment treatment){
        this.treatmentDao.updateTreatment(treatment);
    }

    public void insertTreatment(Treatment treatment) {
        this.treatmentDao.insertTreatment(treatment);
    }
}