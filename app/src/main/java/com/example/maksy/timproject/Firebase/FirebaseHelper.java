package com.example.maksy.timproject.Firebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.AvailableAppointments;
import com.example.maksy.timproject.Appointments.CreateAppointment;
import com.example.maksy.timproject.Entity.AppoDoc;
import com.example.maksy.timproject.Appointments.PatientAppointments;
import com.example.maksy.timproject.Entity.Appointment;
import com.example.maksy.timproject.Entity.Treatment;
import com.example.maksy.timproject.Login.AfterLogin.AfterLogin;
import com.example.maksy.timproject.Login.LoginActivity;
import com.example.maksy.timproject.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private String userId;
    private String reference;
    private Context context;
    private List<AppoDoc> appointments = new ArrayList<>();
    private List<String> treatmentsList = new ArrayList<>();

    public FirebaseHelper(String reference, Context context) {
        this.reference = reference;
        this.context = context;
        initFirebase();
    }

    public void initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(reference);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void createUser(String name, String surname, String pesel, String sex, String email, String phone, String password, Integer type, String specialization) {
        userId = databaseReference.push().getKey();

        User user = new User(userId, name, surname, pesel, sex, email, phone, password, type, specialization);
        databaseReference.child(userId).setValue(user);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(context.getApplicationContext(), "createUserWithEmail!" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                        if (!task.isSuccessful()) {
                            Toast.makeText(context.getApplicationContext(), "Authentication failed!" + task.getException(), Toast.LENGTH_LONG).show();
                        } else {
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                    }
                });
    }

    public void loginToAccount(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(context.getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Authentication GOOD", Toast.LENGTH_LONG).show();
                            context.startActivity(new Intent(context, AfterLogin.class));
                        }
                    }
                });
    }

    public void cancelPatientAppointment(String key, String reference) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(reference);
        databaseReference.child(key).child("patientEmail").setValue("");
        databaseReference.child(key).child("flag").setValue(0);
    }

    public void signUpForAppointment(int position){
        firebaseAuth = FirebaseAuth.getInstance();
        String email = firebaseAuth.getCurrentUser().getEmail();

        final AppoDoc appoDoc = appointments.get(position);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        databaseReference.child(appoDoc.getKey()).child("patientEmail").setValue(email);
        databaseReference.child(appoDoc.getKey()).child("flag").setValue(1);
    }

    public void prepareDataForPatientAppointments(int flag) {
        Query query;
        databaseReference = firebaseDatabase.getReference("appointments");
        if (flag == 0) {
            firebaseAuth = FirebaseAuth.getInstance();
            String email = firebaseAuth.getCurrentUser().getEmail();
            query = databaseReference.orderByChild("patientEmail").equalTo(email);
        } else if(flag == 1){
            query = databaseReference.orderByChild("patientEmail").equalTo("");
        } else {
            query = databaseReference.orderByChild("patientEmail");
        }
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String doctorEmail = (String) dataSnapshot1.child("doctorEmail").getValue();
                    Long date = (Long) dataSnapshot1.child("date").getValue();
                    String treatName = (String) dataSnapshot1.child("treatName").getValue();

                    String key = dataSnapshot1.getKey();
                    getDoctorName(key, doctorEmail, treatName, date);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void getDoctorName(final String key, final String doctorEmail, final String treatName, final Long date) {

        databaseReference = firebaseDatabase.getReference("users");

        Query query = databaseReference.orderByChild("email").equalTo(doctorEmail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = (String) dataSnapshot1.child("firstName").getValue() + " " + (String) dataSnapshot1.child("lastName").getValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    String dateString = dateFormat.format(date);
                    AppoDoc appointment = new AppoDoc(key, treatName, name, doctorEmail, dateString);
                    appointments.add(appointment);
                    if (context instanceof PatientAppointments) {
                        ((PatientAppointments) context).notifyAdapter();
                        Log.i("PatientAppo","Patientapp notify");
                    } else if (context instanceof AvailableAppointments){
                        ((AvailableAppointments) context).notifyAdapter();
                        Log.i("AvailableAppo","Patientapp notify");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void createTreatment(String treatName,String treatDoc,String treatDesc) {
        String treatId;
        databaseReference = firebaseDatabase.getReference("treatments");
        treatId= databaseReference.push().getKey();
        Treatment treatment = new Treatment(treatName,treatDoc,treatDesc);
        databaseReference.child(treatId).setValue(treatment);
    }

    public void getTreatmentsFromFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("treatments");
        Query query = databaseReference.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = (String) dataSnapshot1.child("name").getValue();
                    Log.i("CREATEAPPO", "name" + name);
                    treatmentsList.add(name);
                    if (context instanceof CreateAppointment) {
                        ((CreateAppointment) context).notifyAdapter();
                        Log.i("PatientAppo","Patientapp notify");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void createAppointent(String hour, String treatname, Long date2) {
        String appoId;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        appoId = databaseReference.push().getKey();
        Appointment appointment = new Appointment(treatname,hour,firebaseAuth.getCurrentUser().getEmail(), date2,"",0);
        databaseReference.child(appoId).setValue(appointment);
    }

    public List<String> getTreatmentsList() {
        return treatmentsList;
    }

    public List<AppoDoc> getAppointments() {
        return appointments;
    }
}
