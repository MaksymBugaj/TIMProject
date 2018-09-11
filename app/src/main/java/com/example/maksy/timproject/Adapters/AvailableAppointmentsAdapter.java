package com.example.maksy.timproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.maksy.timproject.Entity.AppoDoc;
import com.example.maksy.timproject.Appointments.AvailableAppointments;
import com.example.maksy.timproject.R;

import java.util.List;

public class AvailableAppointmentsAdapter extends RecyclerView.Adapter<AvailableAppointmentsAdapter.MyViewHolder> {

    private List<AppoDoc> appointments;
    private Context context;

    public AvailableAppointmentsAdapter(List<AppoDoc> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_appo_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final AppoDoc appointment = appointments.get(i);
        String info = appointment.getDate() + "\n" + "Doctor " + appointment.getDoctorName() +"\n" +appointment.getTreatName();
        myViewHolder.mAppoInfo.setText(info);


        myViewHolder.buttonRecyclerOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, myViewHolder.buttonRecyclerOptions);
                popupMenu.inflate(R.menu.recycler_button);
                MenuItem menuItem = popupMenu.getMenu().findItem(R.id.assignToMe);
                menuItem.setVisible(false);
                MenuItem menuItem1 = popupMenu.getMenu().findItem(R.id.resign);
                menuItem1.setVisible(false);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.signupForTreat:
                                if (context instanceof AvailableAppointments) {
                                    ((AvailableAppointments) context).firebaseHelper.signUpForAppointment(i);
                                }
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView mAppoInfo;
        public Button buttonRecyclerOptions;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppoInfo = (TextView) itemView.findViewById(R.id.appo_info);
            buttonRecyclerOptions = (Button) itemView.findViewById(R.id.appo_button);
        }
    }
}
