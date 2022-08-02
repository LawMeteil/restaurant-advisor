package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Retrofit retrofit;
    private static RestaurantAPI restaurantApi;
    private List<Users> usersList;
    private EditText edit_login;
    private EditText edit_password;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Recupération du boutton
         */
        Button button = (Button) findViewById(R.id.test);
        this.edit_login = (EditText) findViewById(R.id.edit_login);
        this.edit_password = (EditText) findViewById(R.id.edit_password);
        this.button_login = (Button) findViewById(R.id.button_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_login.getText().toString().isEmpty() && edit_password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Les champs sont vides", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = edit_login.getText().toString();
                String password = edit_password.getText().toString();
                LoginRequest auth = new LoginRequest(login, password);

                /**
                 * Lancement de l'activité 2
                 */
                LoginViaAPI(auth);

            }
        });
        configureRetrofit();
        getUsersViaAPI();

    }

    private void setButton_login() {
        Intent login = new Intent(this,MainActivity2.class);
        startActivity(login);
    }

    private void test() {
        /**
         * Lance l'activité MainActivity2
         */
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void configureRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restaurantApi = retrofit.create(RestaurantAPI.class);
    }

    private void LoginViaAPI(LoginRequest auth) {
        Call<LoginResponse> call = restaurantApi.login(auth);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    System.out.println(loginResponse.getToken());
                    setButton_login();
                } else {
                    System.out.println("non");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void getUsersViaAPI() {
        restaurantApi.getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.d(TAG,"onResponse");

                List<Users> usersList = response.body();
                if (usersList !=null) {
                    for (Users users: usersList){
                        Log.d(TAG,"users reçu:" + users.getId() + " " + users.getLogin() + " " + users.getPassword() + " " + users.getEmail() + " " + users.getName() + " " + users.getFirstname() + " " + users.getAge());
                    }

                } else {
                    Log.d(TAG, "onResponse: users is empty:" + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

}