package com.example.maksy.timproject.Calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.maksy.timproject.Appointments.CreateAppointment;
import com.example.maksy.timproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.calendar_view)
    CalendarView calendarView;
    @BindView(R.id.calendar_welcome_text)
    TextView calendarText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ButterKnife.bind(this);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Intent intent = new Intent(getApplicationContext(), CreateAppointment.class);
                intent.putExtra("year",String.valueOf(i));
                intent.putExtra("month",String.valueOf(i1 + 1));
                intent.putExtra("day",String.valueOf(i2));
                startActivity(intent);

            }
        });


    }

}
