package com.example.maksy.timproject.Appointments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.maksy.timproject.Adapters.PatientAppointmentAdapter;
import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.R;

public class PatientAppointments extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PatientAppointmentAdapter patientAppointmentAdapter;

    public FirebaseHelper firebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_appo);

        prepareFirebase();
        firebaseHelper.prepareDataForPatientAppointments(0);

        patientAppointmentAdapter = new PatientAppointmentAdapter(firebaseHelper.getAppointments(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(patientAppointmentAdapter);
    }

    public void notifyAdapter() {
        patientAppointmentAdapter.notifyDataSetChanged();
    }

    private void prepareFirebase() {
        String initFirebase = "appointments";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }
}
