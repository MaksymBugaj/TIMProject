package com.example.maksy.timproject.Calendar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksy.timproject.R;
import com.example.maksy.timproject.User.Doctor;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {


    private List<Doctor> doctors;

    public CalendarAdapter(List<Doctor> doctors) {
        this.doctors = doctors;
        Log.i("here?", "construct");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDoctorName,specialization;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDoctorName = (TextView) itemView.findViewById(R.id.doctor_name);
            specialization = (TextView) itemView.findViewById(R.id.doctor_specialization);
        }
    }

    @Override
    public CalendarAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_calendar_date_row, viewGroup, false);
        Log.i("here?", "oncreate");
        return new CalendarAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CalendarAdapter.MyViewHolder myViewHolder, int position) {
        final Doctor doctor = doctors.get(position);
        myViewHolder.mDoctorName.setText(doctor.getName());
        myViewHolder.specialization.setText(doctor.getSpecialization());
        Log.i("here?", "setText");

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }


}
