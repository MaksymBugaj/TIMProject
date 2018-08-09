package com.example.maksy.timproject.Calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.maksy.timproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CalendarChooseDate extends AppCompatActivity {

    @BindView(R.id.choose_date_text)
    TextView chooseDate;

    private String day,month,year;
    private Unbinder unbinder;

    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_choose_date);

        ButterKnife.bind(this);
        unbinder=ButterKnife.bind(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        getData();
        setTextView();

        calendarAdapter = new CalendarAdapter();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(calendarAdapter);
    }

    private void setTextView() {
        String textToSet = "Available dates"  + " for: " + day +"." + month  +"." + year;
        chooseDate.setText(textToSet);
    }

    private void getData() {
        Intent intent = getIntent();
        this.day=intent.getStringExtra("day");
        this.month=intent.getStringExtra("month");
        this.year=intent.getStringExtra("year");
    }
}
