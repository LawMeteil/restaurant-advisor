package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private static RestaurantAPI restaurantApi;
    private List<Users> Users;
    private Retrofit retrofit;
    private EditText edit_login;
    private EditText edit_password;
    private EditText edit_email;
    private EditText edit_name;
    private EditText edit_firstname;
    private EditText edit_age;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        this.edit_login = (EditText) findViewById(R.id.edit_login);
        this.edit_password = (EditText) findViewById(R.id.edit_password);
        this.edit_email = (EditText) findViewById(R.id.edit_email);
        this.edit_name = (EditText) findViewById(R.id.edit_name);
        this.edit_firstname = (EditText) findViewById(R.id.edit_firstname);
        this.edit_age = (EditText) findViewById(R.id.edit_age);
        this.sign_up = (Button) findViewById(R.id.sign_up);


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = edit_login.getText().toString();
                String password = edit_password.getText().toString();
                String email = edit_email.getText().toString();
                String name = edit_name.getText().toString();
                String firstname = edit_firstname.getText().toString();
                String age = edit_age.getText().toString();
                Users register = new Users(login, password, email, name, firstname, age);
                registerData(register);
            }
        });
        configureRetrofit();

    }

    private void configureRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restaurantApi = retrofit.create(RestaurantAPI.class);
    }

    private void registerData(Users register) {
        Call<Users> call = restaurantApi.register(register);
        call.enqueue(new Callback<com.example.myapplication.Users>() {
            @Override
            public void onResponse(Call<com.example.myapplication.Users> call, Response<com.example.myapplication.Users> response) {
                Users user = response.body();
                if (user != null) {
                    System.out.println(user.getName());

                }
            }

            @Override
            public void onFailure(Call<com.example.myapplication.Users> call, Throwable t) {

            }
        });
    }
}