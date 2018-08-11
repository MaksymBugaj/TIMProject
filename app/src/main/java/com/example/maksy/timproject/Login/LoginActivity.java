package com.example.maksy.timproject.Login;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.maksy.timproject.Calendar.CalendarActivity;
import com.example.maksy.timproject.Login.AfterLogin.AfterLogin;
import com.example.maksy.timproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.appCompatButtonLogin)
    Button button;

    private NestedScrollView nestedScrollView;
    private AnimationDrawable animationDrawable;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        animationDrawable = (AnimationDrawable) nestedScrollView.getBackground();

        animationDrawable.setEnterFadeDuration(5000);

        animationDrawable.setExitFadeDuration(2000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }

    @OnClick(R.id.appCompatButtonLogin)
    public void onLoginButtonClick() {
        startActivity(new Intent(this, AfterLogin.class));
    }

    @OnClick(R.id.textViewLinkRegister)
    public void onNoAccountClick() {
        startActivity(new Intent(this, CreateAccount.class));
    }
}
