package com.example.maksy.timproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.maksy.timproject.Locale.LocaleHelper;
import com.example.maksy.timproject.Login.LoginActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String mLanguageCode = "pl";
    Button button;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = LocaleHelper.setLocale(MainActivity.this, "en");
        resources = context.getResources();

        button = (Button)findViewById(R.id.changeLanguage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(MainActivity.this, "pl");
                resources = context.getResources();
                recreate();
                Log.i("Language",mLanguageCode);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.loginclass:
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
