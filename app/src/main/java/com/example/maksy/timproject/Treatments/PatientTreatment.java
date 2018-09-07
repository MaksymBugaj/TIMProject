package com.example.maksy.timproject.Treatments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PatientTreatment extends AppCompatActivity {

    @BindView(R.id.spinnerTreatments)
    Spinner patientTreatments;
    @BindView(R.id.patientTreatment)
    TextView patientTreatment;
    @BindView(R.id.buttonSubmitTreatment)
    Button buttonSubmit;


    private Unbinder unbinder;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_treatment);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSubmitTreatment)
    public void onSubmitClick(){
        String toFill = "toFill";
        String treatId;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("treatments");
        treatId= databaseReference.push().getKey();
        Treatment treatment = new Treatment(treatId,toFill,toFill,patientTreatments.getSelectedItem().toString(),toFill,firebaseAuth.getCurrentUser().getEmail());
        databaseReference.child(treatId).setValue(treatment);
        Toast.makeText(this, "Created Treatment", Toast.LENGTH_SHORT).show();
        finish();
    }
}
