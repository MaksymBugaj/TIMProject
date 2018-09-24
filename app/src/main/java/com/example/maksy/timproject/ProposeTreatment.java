package com.example.maksy.timproject;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.maksy.timproject.Firebase.FirebaseHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ProposeTreatment extends AppCompatActivity {

    @BindView(R.id.textInputEditTextTreatmentName)
    TextInputEditText treatmentName;
    @BindView(R.id.textInputEditTextTreatmentDoctor)
    TextInputEditText treatmentDoctor;
    @BindView(R.id.textInputLayoutTreatmentDoctor)
    TextInputLayout layout;
    @BindView(R.id.textInputEditTextTreatmentDescription)
    TextInputEditText treatmentDescription;
    @BindView(R.id.buttonSubmitTreatment)
    Button buttonSubmit;

    public FirebaseHelper firebaseHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_treatment);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        treatmentDoctor.setVisibility(View.GONE);
        layout.setVisibility(View.GONE);
        prepareFirebase();

    }

    @OnClick(R.id.buttonSubmitTreatment)
    public void onSubmitClick() {
        firebaseHelper.createTreatmentProposition(treatmentName.getText().toString(), treatmentDescription.getText().toString());
        Toast.makeText(this, "Created Treatment Proposition", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void prepareFirebase() {
        String initFirebase = "proposeTreatment";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }

}
