package com.example.maksy.timproject.Calendar;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.R;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class CalendarTestSpinner extends AppCompatActivity {



    private Unbinder unbinder;

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_test_spinner);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);

        prepareData();



    }

   /* private void prepareAdapter() {
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, list){

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position%2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#FFC9A3FF"));

                }
                else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#FFAF89E5"));
                }
                return view;
            }


        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctors.setAdapter(dataAdapter);
        spinnerDoctors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("HELP","HELP");
                ((TextView) view).setTextColor(Color.RED);
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),
                        "Selected Country : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }*/

    private void prepareData() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("appointments");
        Query query = databaseReference.orderByChild("patientID").equalTo("");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String doctorName = (String) dataSnapshot1.child("doctorName").getValue() + " " + (String) dataSnapshot1.child("doctorSurname").getValue();
                    // String appointmentName = (String) dataSnapshot1.child("name").getValue();
                    /*String date = (String) dataSnapshot1.child("date").getValue();
                    String doctorId = (String) dataSnapshot1.child("doctorID").getValue();*/
                    Log.i("POMOCY!!!!1?", doctorName);
                   /* list.add(doctorName);
                    Log.i("list", list.get(0));*/

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @OnClick(R.id.buttonSubmitDoctor)
    public void onSubmitClick(){
       //
       // Toast.makeText(this, dupa, Toast.LENGTH_SHORT).show();
    }

}
