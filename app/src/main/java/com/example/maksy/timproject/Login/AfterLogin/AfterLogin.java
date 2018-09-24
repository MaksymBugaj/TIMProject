package com.example.maksy.timproject.Login.AfterLogin;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.maksy.timproject.AddOpinion;
import com.example.maksy.timproject.Appointments.AvailableAppointments;
import com.example.maksy.timproject.Appointments.DoctorPatient.DoctorPatients;
import com.example.maksy.timproject.Appointments.PatientAppointments;
import com.example.maksy.timproject.Calendar.CalendarActivity;
import com.example.maksy.timproject.OpinionsActivity;
import com.example.maksy.timproject.ProposeTreatment;
import com.example.maksy.timproject.R;
import com.example.maksy.timproject.Treatments.AddTreatment;
import com.example.maksy.timproject.Treatments.ShowTreatmentProposition;
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
    @BindView(R.id.afterLoginAvailableAppoList)
    Button availableAppointmentsButton;
    @BindView(R.id.afterLoginPatients)
    Button buttonPatients;
    @BindView(R.id.afterLoginAddTreatments)
    Button buttonAddTermins;
    @BindView(R.id.progresBar)
    ContentLoadingProgressBar progressBar;
    @BindView(R.id.afterLoginShowOnMap)
    Button map;
    @BindView(R.id.opinionButton)
    Button buttonOpinion;
    @BindView(R.id.showOpinionButton)
    Button buttonShowOpinions;
    @BindView(R.id.addTreatmentPropositionButton)
    Button buttonaddTreatmentPropo;
    @BindView(R.id.showTreatmentPropositions)
    Button buttonShowTreatPropo;


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
        availableAppointmentsButton.setVisibility(View.INVISIBLE);
        buttonPatients.setVisibility(View.INVISIBLE);
        buttonVisits.setVisibility(View.INVISIBLE);
        map.setVisibility(View.INVISIBLE);
        buttonOpinion.setVisibility(View.INVISIBLE);
        buttonShowOpinions.setVisibility(View.INVISIBLE);
        buttonaddTreatmentPropo.setVisibility(View.INVISIBLE);
        buttonShowTreatPropo.setVisibility(View.INVISIBLE);
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
        startActivity(new Intent(getApplicationContext(), DoctorPatients.class));
    }

    //pacjent
    @OnClick(R.id.afterLoginAvailableAppoList)
    public void onDoctorsButtonClick(){
        startActivity(new Intent(getApplicationContext(), AvailableAppointments.class));
    }

    @OnClick(R.id.afterLoginVisits)
    public void onVisitsButtonClick(){
        startActivity(new Intent(getApplicationContext(), PatientAppointments.class));
    }

    @OnClick(R.id.afterLoginAddTreatments)
    public void onAddTreatmentsButtonClick(){
        startActivity(new Intent(getApplicationContext(), AddTreatment.class));
    }

    @OnClick(R.id.opinionButton)
    public void onOpinionButtonClick(){
        startActivity(new Intent(getApplicationContext(), AddOpinion.class));
    }

    @OnClick(R.id.showOpinionButton)
    public void onShowOpinionClick(){
        startActivity(new Intent(getApplicationContext(), OpinionsActivity.class));
    }

    @OnClick(R.id.addTreatmentPropositionButton)
    public void onAddTreatmentPropositionClick(){
        startActivity(new Intent(getApplicationContext(), ProposeTreatment.class));
    }

    @OnClick(R.id.showTreatmentPropositions)
    public void onAShowTreatmentPropositionClick(){
        startActivity(new Intent(getApplicationContext(), ShowTreatmentProposition.class));
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
                    long type = (Long) dataSnapshot1.child("type").getValue();
                    Log.i("NOELO", String.valueOf(type));


                        if (type == 1 || type == 2) {
                            progressBar.setVisibility(View.GONE);
                            buttonAddTermins.setVisibility(View.VISIBLE);
                            buttonCalendar.setVisibility(View.VISIBLE);
                            availableAppointmentsButton.setVisibility(View.GONE);
                            buttonPatients.setVisibility(View.VISIBLE);
                            buttonVisits.setVisibility(View.GONE);
                            buttonOpinion.setVisibility(View.GONE);
                            buttonShowOpinions.setVisibility(View.GONE);
                            buttonaddTreatmentPropo.setVisibility(View.GONE);
                            buttonShowTreatPropo.setVisibility(View.VISIBLE);
                        } else if(type == 0){
                            progressBar.setVisibility(View.GONE);
                            buttonAddTermins.setVisibility(View.GONE);
                            buttonCalendar.setVisibility(View.GONE);
                            availableAppointmentsButton.setVisibility(View.VISIBLE);
                            buttonPatients.setVisibility(View.GONE);
                            buttonVisits.setVisibility(View.VISIBLE);
                            map.setVisibility(View.VISIBLE);
                            buttonOpinion.setVisibility(View.VISIBLE);
                            buttonShowOpinions.setVisibility(View.VISIBLE);
                            buttonaddTreatmentPropo.setVisibility(View.VISIBLE);
                            buttonShowTreatPropo.setVisibility(View.GONE);
                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.afterLoginShowOnMap)
    public void showOnMap(){
        String lat = "52.250570";
        String lng = "20.895178";
        String url = "http://maps.google.com/maps?addr=" + lat + "," + lng;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<"+lat+">,<"+lng+">?q=<"+lat+">,<"+lng+">(Twoja+Przychodnia)"));
        startActivity(intent);
        }
}
