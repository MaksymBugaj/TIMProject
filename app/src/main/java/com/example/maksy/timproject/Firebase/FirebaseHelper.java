package com.example.maksy.timproject.Firebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.maksy.timproject.Login.AfterLogin.AfterLogin;
import com.example.maksy.timproject.Login.CreateAccount;
import com.example.maksy.timproject.Login.LoginActivity;
import com.example.maksy.timproject.MainActivity;
import com.example.maksy.timproject.R;
import com.example.maksy.timproject.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class FirebaseHelper {

    private FirebaseAuth auth;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private String reference;
    private Context context;

    public FirebaseHelper(String reference, Context context) {
        this.reference = reference;
        this.context = context;
        initFirebase();
    }

    public void initFirebase() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference(reference);
        auth = FirebaseAuth.getInstance();

    }

    public void createUser(String name, String surname,String pesel,String sex, String email, String phone, String password,String type,String specialization) {
        userId = mDatabaseReference.push().getKey();

        User user = new User(userId, name, surname,pesel,sex, email, phone, password, type,specialization);
        mDatabaseReference.child(userId).setValue(user);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(context.getApplicationContext(), "createUserWithEmail!" + task.isSuccessful(), Toast.LENGTH_LONG).show();

                        if (!task.isSuccessful()) {
                            Toast.makeText(context.getApplicationContext(), "Authentication failed!" + task.getException(), Toast.LENGTH_LONG).show();
                        } else {
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                    }
                });
    }

    public void getDataFromFirebase() {

    }

    public void loginToAccount(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            Toast.makeText(context.getApplicationContext(), "Authentication failed", Toast.LENGTH_LONG).show();
                        } else {
                            context.startActivity(new Intent(context, AfterLogin.class));
                        }
                    }
                });
    }
}
