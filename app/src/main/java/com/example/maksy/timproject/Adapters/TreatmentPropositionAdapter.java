package com.example.maksy.timproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maksy.timproject.Entity.Treatment;
import com.example.maksy.timproject.R;

import java.util.List;

public class TreatmentPropositionAdapter extends RecyclerView.Adapter<TreatmentPropositionAdapter.MyViewHolder> {

    private List<Treatment> treatments;
    private Context context;

    public TreatmentPropositionAdapter(List<Treatment> treatments, Context context) {
        this.treatments = treatments;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_appo_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Treatment treatment = treatments.get(i);
        String info = "Treatment name: " +treatment.getName() + " \n" + "Treatment description: " + treatment.getDesc();
        myViewHolder.mAppoInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return treatments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mAppoInfo;
        public Button buttonRecyclerOptions;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppoInfo = (TextView) itemView.findViewById(R.id.appo_info);
            buttonRecyclerOptions = (Button) itemView.findViewById(R.id.appo_button);
            buttonRecyclerOptions.setVisibility(View.GONE);
        }
    }
}
