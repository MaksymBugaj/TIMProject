package com.example.maksy.timproject.API;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maksy.timproject.R;

import java.io.IOException;

import okhttp3.ResponseBody;
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

        final TextView name = (TextView) findViewById(R.id.retro_test_text);

        final Button button = (Button) findViewById(R.id.retro_test_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://timproject.herokuapp.com/")
                .build();

        final HerokuService service = retrofit.create(HerokuService.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TestUser testUser = new TestUser(name.getText().toString());

              //  sendNetworkRequest(testUser);
                Call<ResponseBody> call = service.hello();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                      if(response.body() != null){
                          try {
                              name.setText(response.body().string());
                          } catch (IOException e) {
                              e.printStackTrace();
                              name.setText(e.getMessage());
                          }
                      } else {
                          name.setText("nodupa");
                      }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        name.setText(t.getMessage());
                    }
                });
            }
        });

    }

    private void testHeroku(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tranquil-inlet-32487.herokuapp.com/")
                .build();

        final HerokuService service = retrofit.create(HerokuService.class);
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
