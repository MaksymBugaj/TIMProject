package com.example.maksy.timproject.Calendar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksy.timproject.R;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> {

    private List<Visit> visits;
    public CalendarAdapter(List<Visit> visits) {
        this.visits=visits;
        Log.i("here?","construct");
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDoctorName, mDoctorSpecialization;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDoctorName = (TextView) itemView.findViewById(R.id.doctor_name);
            mDoctorSpecialization = (TextView) itemView.findViewById(R.id.doctor_hours);
        }
    }

    @Override
    public CalendarAdapter.MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_date_row, viewGroup, false);
        Log.i("here?","oncreate");
        return new CalendarAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CalendarAdapter.MyViewHolder myViewHolder, int position) {
        myViewHolder.mDoctorName.setText(R.string.after_login_welcome);
        myViewHolder.mDoctorSpecialization.setText(R.string.after_login_welcome);
        Log.i("here?","onbind");
    }

    @Override
    public int getItemCount() {
        return visits.size();
    }


}
