package com.example.maksy.timproject.Appointments.DoctorPatient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.R;

public class DoctorPatients extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DoctorPatientsAdapter doctorPatientsAdapter;

    public FirebaseHelper firebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);
        TextView welcomeText = (TextView) findViewById(R.id.appointments);
        welcomeText.setText(R.string.your_patients);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_appo);

        prepareFirebase();
        firebaseHelper.prepareDataForAppointments(2);

        doctorPatientsAdapter = new DoctorPatientsAdapter(firebaseHelper.getDoctorPatients(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(doctorPatientsAdapter);
    }

    public void notifyAdapter() {
        doctorPatientsAdapter.notifyDataSetChanged();
    }

    private void prepareFirebase() {
        String initFirebase = "doctorPatients";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }
}
