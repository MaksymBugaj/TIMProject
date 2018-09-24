package com.example.maksy.timproject.Appointments;

import android.content.Intent;
import android.nfc.Tag;
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

import com.example.maksy.timproject.Entity.Appointment;
import com.example.maksy.timproject.Firebase.FirebaseHelper;
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
    private static final String TAG = CreateAppointment.class.getName();

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

    ArrayAdapter<String> adapter;
    public FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);
        prepareFirebase();
        //firebaseHelper.getTreatmentsFromFirebase();
        firebaseHelper.readData(new FirebaseHelper.FirebaseCallback() {
            @Override
            public void onCallback(List<String> list) {
                Log.i(TAG,"halo xd");
            }
        });
        getData();
        ButterKnife.bind(this);
        setTextView();

    }
    private void prepareFirebase() {
        String initFirebase = "appointments";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }

    private void initSpinner() {
        adapter = new ArrayAdapter<String>(CreateAppointment.this, android.R.layout.simple_spinner_dropdown_item, firebaseHelper.getTreatmentsList());
        spinner.setAdapter(adapter);
    }


    public void notifyAdapter(){
        initSpinner();
        adapter.notifyDataSetChanged();
    }

    private void getData() {
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
        firebaseHelper.createAppointent(hour, treatname, date2);
        Toast.makeText(this, "Created Appointment", Toast.LENGTH_SHORT).show();
        finish();
    }


}
