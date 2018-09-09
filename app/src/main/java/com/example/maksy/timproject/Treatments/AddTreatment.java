package com.example.maksy.timproject.Treatments;

import android.support.design.widget.TextInputEditText;
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

public class AddTreatment extends AppCompatActivity {

    @BindView(R.id.textInputEditTextTreatmentName)
    TextInputEditText treatmentName;
    @BindView(R.id.textInputEditTextTreatmentDoctor)
    TextInputEditText treatmentDoctor;
    @BindView(R.id.textInputEditTextTreatmentDescription)
    TextInputEditText treatmentDescription;
    @BindView(R.id.buttonSubmitTreatment)
    Button buttonSubmit;


    private Unbinder unbinder;
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
        String treatId;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("treatments");
        treatId= databaseReference.push().getKey();
        Treatment treatment = new Treatment(treatmentName.getText().toString(),treatmentDoctor.getText().toString(),treatmentDescription.getText().toString());
        databaseReference.child(treatId).setValue(treatment);
        Toast.makeText(this, "Created Treatment", Toast.LENGTH_SHORT).show();
        finish();
    }
}
