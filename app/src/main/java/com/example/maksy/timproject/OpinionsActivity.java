package com.example.maksy.timproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.maksy.timproject.Adapters.OpinionsAdapter;
import com.example.maksy.timproject.Entity.Opinions;
import com.example.maksy.timproject.Firebase.FirebaseHelper;

import java.util.List;

public class OpinionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OpinionsAdapter opinionsAdapter;
    public FirebaseHelper firebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinions);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_opinions);

        prepareFirebase();
        firebaseHelper.getOpinion(new FirebaseHelper.FirebaseObjectCallback() {
            @Override
            public void onCallback(List list) {
                notifyAdapter();
            }
        });

        opinionsAdapter = new OpinionsAdapter(firebaseHelper.getOpinions(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(opinionsAdapter);
    }

    private void prepareFirebase() {
        String initFirebase = "opinions";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
    }
    public void notifyAdapter(){
        opinionsAdapter.notifyDataSetChanged();
    }
}
