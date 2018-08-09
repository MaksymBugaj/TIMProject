package com.example.maksy.timproject.Calendar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksy.timproject.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {

    public CalendarAdapter() {
    }

    @NonNull
    @Override
    public CalendarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_date_row,viewGroup,false);
        return new CalendarAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.MyViewHolder myViewHolder, int i) {
myViewHolder.mDoctorName.setText("Krzysztof");
myViewHolder.mDoctorHours.setText("10:00-15:00");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDoctorName, mDoctorHours;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDoctorName = (TextView) itemView.findViewById(R.id.doctor_name);
            mDoctorHours = (TextView) itemView.findViewById(R.id.doctor_hours);
        }
    }
}
