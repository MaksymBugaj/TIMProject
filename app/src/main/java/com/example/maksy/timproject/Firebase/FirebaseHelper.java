package com.example.maksy.timproject.Firebase;

import com.example.maksy.timproject.User.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private String reference;

    public FirebaseHelper(String reference) {
        this.reference = reference;
        initFirebase();
    }

    public void initFirebase() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseInstance.getReference(reference);

    }

    public void createUser(String name, String surname, String email,String phone,String login, String password) {
        userId = mDatabaseReference.push().getKey();

        User user = new User(userId, name, surname, email, phone, login, password, false);
        mDatabaseReference.child(userId).setValue(user);

    }
}
