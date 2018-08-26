package com.maksy.Dao;

import com.maksy.Entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DoctorDao {

    private static Map<Integer, Doctor> doctors;
//fixme kick this shit out
    static {
        doctors = new HashMap<Integer, Doctor>(){
            {
                put(1, new Doctor(1,"Marcin","Borowski"));
                put(2, new Doctor(2,"Maksym","Bugaj"));
                put(3, new Doctor(3,"Mariusz","Byler"));
                put(4, new Doctor(4,"Łukasz","Budner"));
            }
        };
    }

    public Collection<Doctor> getAllDoctors(){
        return this.Doctors.values();
    }

    public Doctor getDoctorById(int id){
        return this.Doctors.get(id);
    }

    public void removeDoctorById(int id) {
        this.Doctors.remove(id);
    }

    public void updateDoctor(Doctor doctor){
        Doctor Doctor1 = doctors.get(doctor.getId());
        Doctor1.setFirstname(doctor.getFirstname());
        Doctor1.setSurname(doctor.getSurname());
        Doctors.put(doctor.getId(),Doctor);
    }

    public void insertDoctor(Doctor doctor) {
        this.Doctors.put(doctor.getId(),Doctor);
    }
}