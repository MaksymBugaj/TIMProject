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

import com.example.maksy.timproject.Appointments.DoctorPatient.DoctorPatients;
import com.example.maksy.timproject.Entity.DocPatient;
import com.example.maksy.timproject.R;

import java.util.List;

public class DoctorPatientsAdapter extends RecyclerView.Adapter<DoctorPatientsAdapter.MyViewHolder>{

    private List<DocPatient> docPatients;
    private Context context;

    public DoctorPatientsAdapter(List<DocPatient> docPatients, Context context) {
        this.docPatients = docPatients;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorPatientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_appo_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoctorPatientsAdapter.MyViewHolder myViewHolder, int i) {
        final DocPatient patient = docPatients.get(i);
        String info = patient.getDate() + "\n" + "Patient " + patient.getPatientName() +"\n" +patient.getTreatName();
        myViewHolder.mAppoInfo.setText(info);


        myViewHolder.buttonRecyclerOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, myViewHolder.buttonRecyclerOptions);
                popupMenu.inflate(R.menu.recycler_button);
                MenuItem menuItem = popupMenu.getMenu().findItem(R.id.assignToMe);
                menuItem.setVisible(false);
                MenuItem menuItem1 = popupMenu.getMenu().findItem(R.id.signupForTreat);
                menuItem1.setVisible(false);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.resign:
                                if (context instanceof DoctorPatients) {
                                    ((DoctorPatients) context).firebaseHelper.cancelPatientAppointment(patient.getKey(),"appointments");
                                    ((DoctorPatients) context).finish();
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
        return docPatients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mAppoInfo;
        public Button buttonRecyclerOptions;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppoInfo = (TextView) itemView.findViewById(R.id.appo_info);
            buttonRecyclerOptions = (Button) itemView.findViewById(R.id.appo_button);
        }
    }
}
