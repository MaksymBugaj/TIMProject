package com.example.maksy.timproject.Appointments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.Appo.AppoDoc;
import com.example.maksy.timproject.Appointments.Appo.Appointment;
import com.example.maksy.timproject.R;
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

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AvailableAppointments extends AppCompatActivity {

    private List<AppoDoc> appointments = new ArrayList<>();
    private RecyclerView recyclerView;
    private AvailableAppointmentsAdapter availableAppointmentsAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_appointments);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_available_appo);

        prepareData();

        availableAppointmentsAdapter = new AvailableAppointmentsAdapter(appointments,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(availableAppointmentsAdapter);
    }

    private void prepareData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
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
                    /*String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorID").getValue();*/
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

    public void signUp(int position){
        firebaseAuth = FirebaseAuth.getInstance();
        String email = firebaseAuth.getCurrentUser().getEmail();

        final AppoDoc appoDoc = appointments.get(position);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        databaseReference.child(appoDoc.getKey()).child("patientEmail").setValue(email);
        databaseReference.child(appoDoc.getKey()).child("flag").setValue(1);
        Toast.makeText(this, "Signed for Treatment", Toast.LENGTH_SHORT).show();
        finish();
    }
}
