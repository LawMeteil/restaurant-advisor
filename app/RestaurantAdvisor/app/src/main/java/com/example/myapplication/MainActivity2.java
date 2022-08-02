package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2<AppBarConfiguration> extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private static final String TAG = "MainActivity";

    private static RestaurantAPI restaurantApi;
    private Retrofit retrofit;

    private MyListViewAdapter myListViewAdapter;
    private EditText editText;
    private ListView listView;
    private Button addRestaurantBtn;

    private List<Restaurant> restaurants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.listView);
        this.addRestaurantBtn = (Button) findViewById(R.id.buttonAddRestaurant);
        this.editText = (EditText) findViewById(R.id.et_restname);

        this.myListViewAdapter = new MyListViewAdapter(getApplicationContext(), restaurants);
        this.listView.setAdapter(myListViewAdapter);


        addRestaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editText.getText().toString().trim();

                Restaurant restaurant1 = new Restaurant();
                restaurant1.setName(name);
                restaurant1.setId("4568");
                restaurant1.setDescription("pas mal");
                restaurant1.setGrade("5");
                restaurant1.setLocalization("paris");
                restaurant1.setHours("24h");
                restaurant1.setPhone_number("0111111111");
                restaurant1.setWebsite("site.fr");

                restaurants.add(restaurant1);

                myListViewAdapter.notifyDataSetChanged();
            }
        });

        // Retrofit

        this.configureRetrofit();

        // Restaurants List

        getRestaurantsViaAPI();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

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

    private void getRestaurantsViaAPI() {

        restaurantApi.getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                Log.d(TAG, "onResponse");

                List<Restaurant> restaurantList = response.body();
                if (restaurantList != null) {

                    restaurants.addAll(restaurantList);
                    myListViewAdapter.notifyDataSetChanged();

                } else {
                    Log.d(TAG, "onResponse: restaurants is empty: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e(TAG, "on Failure: " + t.getMessage());
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}