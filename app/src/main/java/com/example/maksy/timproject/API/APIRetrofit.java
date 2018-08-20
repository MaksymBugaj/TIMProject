package com.example.maksy.timproject.API;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maksy.timproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiretrofit);

        final EditText name = (EditText) findViewById(R.id.retro_test_text);

        Button button = (Button) findViewById(R.id.retro_test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestUser testUser = new TestUser(name.getText().toString());

                sendNetworkRequest(testUser);

            }
        });

    }

    private void sendNetworkRequest(TestUser testUser){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<TestUser> call = client.createAccount(testUser);

        call.enqueue(new Callback<TestUser>() {
            @Override
            public void onResponse(Call<TestUser> call, Response<TestUser> response) {
                Toast.makeText(APIRetrofit.this, ":_)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TestUser> call, Throwable t) {
                Toast.makeText(APIRetrofit.this, ":(((((", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
