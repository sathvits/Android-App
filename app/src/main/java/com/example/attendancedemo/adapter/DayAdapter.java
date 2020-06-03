package com.example.attendancedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class DayAdapter extends BaseAdapter {
    Context context;
    String[] day_name;
    LayoutInflater inflter;
    public DayAdapter(Activity applicationContext, String[] day_name) {
        this.context = applicationContext;
        this.day_name = day_name;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return day_name.length;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_table, null); // inflate the layout
        EditText editText = (EditText) view.findViewById(R.id.day); // get the reference of ImageView
        editText.setText(day_name[i]);
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}
