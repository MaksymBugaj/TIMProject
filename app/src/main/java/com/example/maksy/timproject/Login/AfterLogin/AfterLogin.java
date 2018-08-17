package com.example.maksy.timproject.Login.AfterLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.maksy.timproject.Calendar.CalendarActivity;
import com.example.maksy.timproject.Calendar.CalendarChooseDate;
import com.example.maksy.timproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AfterLogin extends AppCompatActivity {

    @BindView(R.id.after_login_welcome)
    TextView welcomeText;
    @BindView(R.id.afterLoginCalendarButton)
    Button buttonCalendar;

    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);
        welcomeText.setText(R.string.after_login_welcome);
    }

    @OnClick(R.id.afterLoginCalendarButton)
    public void onCalendarButtonClick(){
        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
