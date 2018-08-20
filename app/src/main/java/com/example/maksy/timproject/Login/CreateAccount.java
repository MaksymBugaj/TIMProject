package com.example.maksy.timproject.Login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.maksy.timproject.Firebase.FirebaseHelper;
import com.example.maksy.timproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CreateAccount extends AppCompatActivity {


    @BindView(R.id.textInputEditTextName)
    TextInputEditText name;
    @BindView(R.id.textInputEditTextSurname)
    TextInputEditText surname;
    @BindView(R.id.textInputEditTextLogin)
    TextInputEditText login;
    @BindView(R.id.textInputEditTextPhone)
    TextInputEditText phone;
    @BindView(R.id.textInputEditTextEmail)
    TextInputEditText email;
    @BindView(R.id.textInputEditTextPassword)
    TextInputEditText password;
    @BindView(R.id.textInputEditTextConfirmPassword)
    TextInputEditText passwordConfirm;

    private FirebaseHelper firebaseHelper;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this);

    }

    @OnClick(R.id.appCompatButtonRegister)
    public void onRegisterButtonClick() {
        String editName = name.getText().toString().trim();
        String editSurname = surname.getText().toString().trim();
        String editEmail = email.getText().toString().trim();
        String editPassword = password.getText().toString().trim();
        String editPasswordConfirm = passwordConfirm.getText().toString().trim();
        String editPhone = phone.getText().toString().trim();
        String editLogin = login.getText().toString().trim();

        if (TextUtils.isEmpty(editName)) {
            Toast.makeText(getApplicationContext(), "Name field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(editSurname)) {
            Toast.makeText(getApplicationContext(), "Surname field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(editEmail)) {
            Toast.makeText(getApplicationContext(), "Email field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(editPassword)) {
            Toast.makeText(getApplicationContext(), "Password field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(editPasswordConfirm)) {
            Toast.makeText(getApplicationContext(), "Password field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!editPassword.equals(editPasswordConfirm)) {
            Toast.makeText(getApplicationContext(), "Password cannot be different", Toast.LENGTH_LONG).show();
            return;
        }


        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "password too short!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() < 9) {
            Toast.makeText(getApplicationContext(), "Phone too short!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(editLogin)) {
            Toast.makeText(getApplicationContext(), "Login field cannot be empty!", Toast.LENGTH_LONG).show();
            return;
        }

        String initFirebase = "users";
        firebaseHelper = new FirebaseHelper(initFirebase, this);
        firebaseHelper.createUser(editName, editSurname, editEmail, editPhone, editLogin, editPassword);
        Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
