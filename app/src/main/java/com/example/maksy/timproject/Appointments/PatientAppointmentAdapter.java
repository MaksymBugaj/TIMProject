package com.example.maksy.timproject.Appointments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maksy.timproject.R;

import java.util.List;

public class PatientAppointmentAdapter extends RecyclerView.Adapter<PatientAppointmentAdapter.MyViewHolder> {

    private List<Appointment> appointments;

    public PatientAppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_appo_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final Appointment appointment = appointments.get(i);
        String info = appointment.getDate() + "\n" + appointment.getDoctorName() + "\n" + appointment.getDoctorSpecialization();
        myViewHolder.mAppoInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mAppoInfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppoInfo = (TextView) itemView.findViewById(R.id.appo_info);

        }
    }


}
