package com.example.attendancedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendancedemo.NamelistActivity;
import com.example.attendancedemo.R;
import com.example.attendancedemo.SectionActivity;

public class HourAdapter extends BaseAdapter {
    Context context;
    String[] hour_name;
    LayoutInflater inflter;
    String period;
    public HourAdapter(Activity applicationContext, String[] hour_name) {
        this.context = applicationContext;
        this.hour_name = hour_name;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return hour_name.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_hour, null); // inflate the layout
        Button button = (Button) view.findViewById(R.id.button); // get the reference of ImageView
        button.setText(hour_name[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period = hour_name[i];
                //(new NamelistActivity()).strPeriod = period;
                Toast.makeText(context,""+period,Toast.LENGTH_LONG).show();
            }
        });
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}
