package com.example.maksy.timproject.Calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.Appointment;
import com.example.maksy.timproject.R;
import com.example.maksy.timproject.RecyclerTouchListener;
import com.example.maksy.timproject.User.Doctor;
import com.example.maksy.timproject.User.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CalendarChooseDate extends AppCompatActivity {

    @BindView(R.id.choose_date_text)
    TextView chooseDate;
    @BindView(R.id.spinnerHours)
    Spinner spinnerHours;


    private String day, month, year;
    private Unbinder unbinder;

    private List<Doctor> doctors = new ArrayList<>();
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_choose_date);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getData();
        setTextView();


        calendarAdapter = new CalendarAdapter(doctors);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(calendarAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Doctor doctor = doctors.get(position);
                createAppointment(doctor);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void setTextView() {
        String textToSet = "Available doctors" + " for: " + day + "." + month + "." + year;
        chooseDate.setText(textToSet);
    }

    private void getData() {
//        Appointment appointment = new Appointment("Eberhard Mock", "Morderstwo");
        //      appointments.add(appointment);
        Intent intent = getIntent();
        this.day = intent.getStringExtra("day");
        this.month = intent.getStringExtra("month");
        this.year = intent.getStringExtra("year");
        prepareData();
    }

    private void prepareData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();
        Query query = databaseReference.orderByChild("type").equalTo("1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String doctorName = (String) dataSnapshot1.child("firstName").getValue() + " " + (String) dataSnapshot1.child("lastName").getValue();
                    String doctorId = (String) dataSnapshot1.child("id").getValue();
                    String specialization = (String) dataSnapshot1.child("specialization").getValue();
                    // String appointmentName = (String) dataSnapshot1.child("name").getValue();
                    /*String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorID").getValue();*/
                    Log.i("POMOCY!!!!1?", doctorName);
                    // setDoctorId(doctorId);
                    Doctor doctor = new Doctor(doctorId, doctorName, specialization);
                    doctors.add(doctor);
                    calendarAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void createAppointment(Doctor doctor) {
        String hour = spinnerHours.getSelectedItem().toString();
        String appoId;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        appoId = databaseReference.push().getKey();
        Appointment appointment = new Appointment(appoId,doctor.getName(), doctor.getSpecialization(), day+"." + month+"." + year+" hour: " + hour, firebaseAuth.getCurrentUser().getEmail());
        databaseReference.child(appoId).setValue(appointment);
        Toast.makeText(this, "Created Appointmnet", Toast.LENGTH_SHORT).show();
        finish();
    }

}
