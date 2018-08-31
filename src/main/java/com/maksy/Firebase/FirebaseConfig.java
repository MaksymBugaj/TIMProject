package com.maksy.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.firebase.internal.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference("users");
        Log.i("FIREBASE","IN THE DATA");
        /*Query query = firebase.orderByChild("email");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String admin = (String) dataSnapshot1.child("firstname").getValue();

                    Log.i("POMOCY!!!!1?", admin);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        return firebase;
    }

    @Value("${rs.pscode.firebase.database.url}")
    private String databaseUrl;

    @Value("${rs.pscode.firebase.config.path}")
    private String configPath;


    @PostConstruct
    public void init() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("timproject-290e2-firebase-adminsdk-e5rnp-7cfaa3de7e.json");


        FirebaseOptions options = new FirebaseOptions.Builder().
                setServiceAccount(serviceAccount)
                .setDatabaseUrl("https://timproject-290e2.firebaseio.com/")
                .build();

        Log.i("FIREBASE","Initialeized");
        FirebaseApp.initializeApp(options);
    }
}
