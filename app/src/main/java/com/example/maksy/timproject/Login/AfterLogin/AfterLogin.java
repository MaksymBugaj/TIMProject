package com.example.maksy.timproject.Login.AfterLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.PatientAppointments;
import com.example.maksy.timproject.Calendar.CalendarActivity;
import com.example.maksy.timproject.R;
import com.example.maksy.timproject.Treatments.PatientTreatment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AfterLogin extends AppCompatActivity {

    @BindView(R.id.afterLoginCalendarButton)
    Button buttonCalendar;
    @BindView(R.id.afterLoginVisits)
    Button buttonVisits;
    @BindView(R.id.afterLoginDoctors)
    Button buttonDoctors;
    @BindView(R.id.afterLoginPatients)
    Button buttonPatients;
    @BindView(R.id.afterLoginAddTreatments)
    Button buttonAddTermins;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private Unbinder unbinder;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);
        buttonAddTermins.setVisibility(View.INVISIBLE);
        buttonCalendar.setVisibility(View.INVISIBLE);
        buttonDoctors.setVisibility(View.INVISIBLE);
        buttonPatients.setVisibility(View.INVISIBLE);
        buttonVisits.setVisibility(View.INVISIBLE);
        if (firebaseAuth.getCurrentUser() != null) {
            UserChangeListener(firebaseAuth.getCurrentUser().getEmail());
        }
    }

    @OnClick(R.id.afterLoginCalendarButton)
    public void onCalendarButtonClick(){
        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
    }



    @OnClick(R.id.afterLoginPatients)
    public void onPatientsButtonClick(){

    }

    //pacjent
    @OnClick(R.id.afterLoginDoctors)
    public void onDoctorsButtonClick(){

    }

    @OnClick(R.id.afterLoginVisits)
    public void onVisitsButtonClick(){
        startActivity(new Intent(getApplicationContext(), PatientAppointments.class));
    }

    @OnClick(R.id.afterLoginAddTreatments)
    public void onAddTreatmentsButtonClick(){
        startActivity(new Intent(getApplicationContext(), PatientTreatment.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void UserChangeListener(String email) {
        Query query = databaseReference.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               /* User user = dataSnapshot.getValue(User.class);
                String name = user.getName();*/
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String type = (String) dataSnapshot1.child("type").getValue();
                    Log.i("NOELO", String.valueOf(type));

                    if(type != null) {
                        if (type.equals("1")) {
                            progressBar.setVisibility(View.INVISIBLE);
                            buttonAddTermins.setVisibility(View.VISIBLE);
                            buttonCalendar.setVisibility(View.GONE);
                            buttonDoctors.setVisibility(View.GONE);
                            buttonPatients.setVisibility(View.VISIBLE);
                            buttonVisits.setVisibility(View.GONE);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            buttonAddTermins.setVisibility(View.VISIBLE);
                            buttonCalendar.setVisibility(View.VISIBLE);
                            buttonDoctors.setVisibility(View.VISIBLE);
                            buttonPatients.setVisibility(View.GONE);
                            buttonVisits.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        Toast.makeText(AfterLogin.this, "Logout and try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
