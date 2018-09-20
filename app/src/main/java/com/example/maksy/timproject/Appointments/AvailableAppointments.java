package com.example.maksy.timproject.Appointments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.maksy.timproject.Adapters.AvailableAppointmentsAdapter;
import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.R;

import butterknife.Unbinder;

public class AvailableAppointments extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AvailableAppointmentsAdapter availableAppointmentsAdapter;

    public FirebaseHelper firebaseHelper;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_appointments);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_available_appo);

        prepareFirebase();
        firebaseHelper.prepareDataForAppointments(1);

        availableAppointmentsAdapter = new AvailableAppointmentsAdapter(firebaseHelper.getAppointments(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(availableAppointmentsAdapter);
    }
    public void notifyAdapter() {
        availableAppointmentsAdapter.notifyDataSetChanged();
    }
    private void prepareFirebase() {
        String initFirebase = "appointments";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }


    /*private void prepareDataForAdapters(String equalTo) {


        databaseReference = firebaseDatabase.getReference("appointments");
        Query query = databaseReference.orderByChild("patientEmail").equalTo("");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String doctorEmail = (String) dataSnapshot1.child("doctorEmail").getValue();
                    Long date = (Long) dataSnapshot1.child("date").getValue();
                    String treatName = (String) dataSnapshot1.child("treatName").getValue();
                    // String appointmentName = (String) dataSnapshot1.child("name").getValue();
                    *//*String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorID").getValue();*//*
                    // setDoctorId(doctorId);
//todo change this
                    String key = dataSnapshot1.getKey();
                    getDoctorName(key,doctorEmail,treatName,date);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getDoctorName(final String key, final String doctorEmail, final String treatName, final Long date) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");


        Query query = databaseReference.orderByChild("email").equalTo(doctorEmail);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String name = (String) dataSnapshot1.child("firstName").getValue() +" " + (String) dataSnapshot1.child("lastName").getValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    String dateString = dateFormat.format(date);
                    AppoDoc appointment = new AppoDoc(key,treatName,name, doctorEmail, dateString);
                    appointments.add(appointment);
                    availableAppointmentsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
*/
    /*public void signUpForAppointment(int position){
        firebaseAuth = FirebaseAuth.getInstance();
        String email = firebaseAuth.getCurrentUser().getEmail();

        final AppoDoc appoDoc = appointments.get(position);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        databaseReference.child(appoDoc.getKey()).child("patientEmail").setValue(email);
        databaseReference.child(appoDoc.getKey()).child("flag").setValue(1);
        Toast.makeText(this, "Signed for Treatment", Toast.LENGTH_SHORT).show();
        finish();
    }*/
}
