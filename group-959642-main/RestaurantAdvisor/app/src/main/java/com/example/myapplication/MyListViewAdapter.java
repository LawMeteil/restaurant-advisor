package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Restaurant> restaurantList;

    public MyListViewAdapter(Context context, List restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.restaurant_row, null);
        }


        Restaurant restaurant = restaurantList.get(position);

        TextView textViewRestaurantName = (TextView) convertView.findViewById(R.id.restaurant_name);
        TextView textViewRestaurantId = (TextView) convertView.findViewById(R.id.restaurant_id);
        TextView textViewRestaurantDescription = (TextView) convertView.findViewById(R.id.restaurant_description);
        TextView textViewRestaurantGrade = (TextView) convertView.findViewById(R.id.restaurant_grade);
        TextView textViewRestaurantLocalization = (TextView) convertView.findViewById(R.id.restaurant_localization);
        TextView textViewRestaurantPhone_number = (TextView) convertView.findViewById(R.id.restaurant_phone_number);
        TextView textViewRestaurantWebsite = (TextView) convertView.findViewById(R.id.restaurant_website);
        TextView textViewRestaurantHours = (TextView) convertView.findViewById(R.id.restaurant_hours);


        textViewRestaurantName.setText(restaurant.getName());
        textViewRestaurantId.setText(restaurant.getId());
        textViewRestaurantDescription.setText(restaurant.getDescription());
        textViewRestaurantGrade.setText(restaurant.getGrade());
        textViewRestaurantLocalization.setText(restaurant.getLocalization());
        textViewRestaurantPhone_number.setText(restaurant.getPhone_number());
        textViewRestaurantWebsite.setText(restaurant.getWebsite());
        textViewRestaurantHours.setText(restaurant.getHours());



        return convertView;
    }
}
