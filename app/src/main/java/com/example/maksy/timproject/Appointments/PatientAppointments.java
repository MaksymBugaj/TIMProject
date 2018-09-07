package com.example.maksy.timproject.Appointments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.maksy.timproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PatientAppointments extends AppCompatActivity {

    private List<Appointment> appointments = new ArrayList<>();
    private RecyclerView recyclerView;
    private PatientAppointmentAdapter patientAppointmentAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_appo);

        prepareData();

        patientAppointmentAdapter = new PatientAppointmentAdapter(appointments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(patientAppointmentAdapter);


    }

    private void prepareData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        firebaseAuth = FirebaseAuth.getInstance();
        String email = firebaseAuth.getCurrentUser().getEmail();
        Query query = databaseReference.orderByChild("patientEmail").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String doctorName = (String) dataSnapshot1.child("doctorName").getValue();
                    String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorSpecialization = (String) dataSnapshot1.child("doctorSpecialization").getValue();
                    // String appointmentName = (String) dataSnapshot1.child("name").getValue();
                    /*String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorID").getValue();*/
                    Log.i("POMOCY!!!!1?", doctorName);
                    // setDoctorId(doctorId);

                    Appointment appointment = new Appointment(doctorName, doctorSpecialization, date);
                    appointments.add(appointment);
                    patientAppointmentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
