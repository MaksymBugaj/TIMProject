package com.example.maksy.timproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.Appointments.CreateAppointment;
import com.example.maksy.timproject.Entity.Opinions;
import com.example.maksy.timproject.Firebase.FirebaseHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOpinion extends AppCompatActivity {
    private static final String TAG = AddOpinion.class.getName();
    @BindView(R.id.spinnerDoctorStars)
    Spinner spinnerStars;
    @BindView(R.id.opinion)
    EditText opinion;
    @BindView(R.id.buttonSubmitOpinion)
    Button button;
    @BindView(R.id.spinnerDoctors)
    Spinner spinnerDoctors;

    ArrayAdapter<String> adapter;
    public FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_opinion);
        ButterKnife.bind(this);

        prepareFirebase();
        firebaseHelper.getDoctors();
    }

    private void initSpinner() {
        adapter = new ArrayAdapter<String>(AddOpinion.this, android.R.layout.simple_spinner_dropdown_item, firebaseHelper.getDoctorsList());
        spinnerDoctors.setAdapter(adapter);
    }


    public void notifyAdapter() {
        initSpinner();
        adapter.notifyDataSetChanged();
    }

    private void prepareFirebase() {
        String initFirebase = "opinions";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }

    @OnClick(R.id.buttonSubmitOpinion)
    public void onButtonClick() {
        firebaseHelper.createOpinion(new FirebaseHelper.FirebaseAddOpinionCallback() {
            @Override
            public void onCallback(List<Opinions> list) {

            }
            
        }, spinnerDoctors.getSelectedItem().toString(), spinnerStars.getSelectedItem().toString(), opinion.getText().toString());
        Toast.makeText(AddOpinion.this, "Opinion added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
