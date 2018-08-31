package com.maksy.Dao;

import com.google.firebase.database.*;
import com.maksy.Entity.Appointment;
import com.maksy.Entity.Treatment;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TreatmentDao {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private static Map<String, Treatment> treatments = new HashMap<>();

    /*static {
        treatments = new HashMap<Integer, Treatment>(){
            {
                put(1, new Treatment(1,"Wycięcie zęba",2));

            }
        };
    }*/

    public void initTreatments() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("treatments");

        Query query = databaseReference.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String id = (String) dataSnapshot1.child("id").getValue();
                    String name = (String) dataSnapshot1.child("name").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorId").getValue();
                    Treatment treatment = new Treatment(id, name, doctorId);
                    treatments.put(treatment.getId(), treatment);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public Collection<Treatment> getAllTreatments() {
        initTreatments();
        return this.treatments.values();
    }

    public Treatment getTreatmentById(String id) {
        return this.treatments.get(id);
    }

    public void removeTreatmentById(String id) {
        this.treatments.remove(id);
    }

    public void updateTreatment(Treatment treatment) {
        /*Treatment treatment1 = treatments.get(treatment.getId());
        treatment1.setName(treatment.getName());
        treatment1.setDoctorID(treatment.getDoctorID());
        treatments.put(treatment.getId(),treatment);*/
    }

    public void insertTreatment(Treatment treatment) {
        initTreatments();
        String treatmentId = databaseReference.push().getKey();
        Treatment treatment1 = new Treatment(treatmentId, treatment.getName(), treatment.getDoctorId());
        databaseReference.child(treatmentId).setValue(treatment1);
    }
}