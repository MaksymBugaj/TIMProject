package com.maksy.Service;

import com.maksy.Dao.DoctorDao;
import com.maksy.Entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DoctorService {

    @Autowired
    private DoctorDao doctorDao;

    public Collection<Doctor> getAllDoctors(){
        return doctorDao.getAllDoctors();
    }

    public Doctor getDoctorById(int id){
        return this.doctorDao.getDoctorById(id);
    }

    public void removeDoctorById(int id) {
        this.doctorDao.removeDoctorById(id);
    }

    public void updateDoctor(Doctor doctor){
        this.doctorDao.updateDoctor(doctor);
    }

    public void insertDoctor(Doctor doctor) {
        this.doctorDao.insertDoctor(doctor);
    }
}