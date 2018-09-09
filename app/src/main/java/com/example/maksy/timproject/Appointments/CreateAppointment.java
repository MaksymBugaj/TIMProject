package com.example.maksy.timproject.Appointments;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.Appo.Appointment;
import com.example.maksy.timproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateAppointment extends AppCompatActivity {

    @BindView(R.id.spinnerTreatments)
    Spinner spinner;
    @BindView(R.id.choose_date_text)
    TextView chooseDate;
    @BindView(R.id.buttonSubmitAppo)
    Button button;
    @BindView(R.id.spinnerTreatmentHours)
    Spinner spinnerHours;

    private String day, month, year,dateString;
    private Unbinder unbinder;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private List<String> treatmentsList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        getDataFromFirebase();
        getData();
        ButterKnife.bind(this);
        setTextView();
    }

    private void initSpinner() {
        adapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_spinner_dropdown_item, treatmentsList);
        spinner.setAdapter(adapter);
    }

    private void getDataFromFirebase() {
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
                    //adapter.notifyDataSetChanged();
                    initSpinner();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void getData() {
//        Appointment appointment = new Appointment("Eberhard Mock", "Morderstwo");
        //      appointments.add(appointment);
        Intent intent = getIntent();
        this.day = intent.getStringExtra("day");
        this.month = intent.getStringExtra("month");
        this.year = intent.getStringExtra("year");
       this.dateString=year +"."+month+"."+day;
       Log.i("StringDate",dateString);

    }

    private void setTextView() {
        String textToSet = "Available doctors" + " for: " + day + "." + month + "." + year;
        chooseDate.setText(textToSet);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.buttonSubmitAppo)
    public void onSubmitButtonClick() {
        String hour = spinnerHours.getSelectedItem().toString();
        String treatname = spinner.getSelectedItem().toString();
        String appoId;
        dateString = dateString +" "+ hour;
        Log.i("StringDate",dateString);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH);
        LocalDate date1 = LocalDate.parse(dateString, dateTimeFormatter);
        Log.i("LongDate1",date1.toString());*/
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Long date2=date.getTime();
        Log.i("LongDate",date.toString());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        appoId = databaseReference.push().getKey();
        Appointment appointment = new Appointment(treatname,hour,firebaseAuth.getCurrentUser().getEmail(), date2,"",0);
        databaseReference.child(appoId).setValue(appointment);
        Toast.makeText(this, "Created Appointment", Toast.LENGTH_SHORT).show();
        finish();
    }
}
