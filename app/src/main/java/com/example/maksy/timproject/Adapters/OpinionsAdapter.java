package com.example.maksy.timproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.maksy.timproject.Entity.Opinions;
import com.example.maksy.timproject.R;

import java.util.List;

public class OpinionsAdapter extends RecyclerView.Adapter<OpinionsAdapter.MyViewHolder>{

    private List<Opinions> opinions;
    private Context context;

    public OpinionsAdapter(List<Opinions> opinions, Context context) {
        this.opinions = opinions;
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
Opinions opinion = opinions.get(i);
String info = "Doctor: " + opinion.getDoctorName()+ ": " + opinion.getRating() +"\n" +"Email: " + opinion.getPatientName() +  "\n" + "Opinion: " +opinion.getOpinion();
myViewHolder.mAppoInfo.setText(info);
    }

    @Override
    public int getItemCount() {
        return opinions.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
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
