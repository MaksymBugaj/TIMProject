package com.example.maksy.timproject.Login;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maksy.timproject.Calendar.CalendarActivity;
import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.Login.AfterLogin.AfterLogin;
import com.example.maksy.timproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.appCompatButtonLogin)
    Button button;
    @BindView(R.id.textInputLoginEmail)
    TextInputEditText email;
    @BindView(R.id.textInputLoginPassword)
    TextInputEditText password;
    @BindView(R.id.loginProgressBar)
    ProgressBar progressBar;

    private NestedScrollView nestedScrollView;
    private AnimationDrawable animationDrawable;

    private Unbinder unbinder;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);
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
        if (TextUtils.isEmpty(email.getText())) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password.getText())) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        String initFirebase = "users";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
        progressBar.setVisibility(View.VISIBLE);
        firebaseHelper.loginToAccount(email.getText().toString(),password.getText().toString());
        progressBar.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.textViewLinkRegister)
    public void onNoAccountClick() {
        startActivity(new Intent(this, CreateAccount.class));
    }
}
