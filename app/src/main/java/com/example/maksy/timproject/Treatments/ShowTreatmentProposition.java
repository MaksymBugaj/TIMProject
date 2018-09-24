package com.example.maksy.timproject.Treatments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.Adapters.AvailableAppointmentsAdapter;
import com.example.maksy.timproject.Adapters.TreatmentPropositionAdapter;
import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.R;

import java.util.List;

import butterknife.Unbinder;

public class ShowTreatmentProposition extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TreatmentPropositionAdapter treatmentPropositionAdapter;

    public FirebaseHelper firebaseHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_appointments);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_available_appo);
        TextView textView = (TextView) findViewById(R.id.available_appointments);
        textView.setText("Treatment propositions");
        prepareFirebase();
        firebaseHelper.getTreatmentProposition(new FirebaseHelper.FirebaseObjectCallback() {
            @Override
            public void onCallback(List list) {
                notifyAdapter();
            }
        });

        treatmentPropositionAdapter = new TreatmentPropositionAdapter(firebaseHelper.getTreatments(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(treatmentPropositionAdapter);
    }
    public void notifyAdapter() {
        treatmentPropositionAdapter.notifyDataSetChanged();
    }

    private void prepareFirebase() {
        String initFirebase = "treatmentsProposition";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }
}
