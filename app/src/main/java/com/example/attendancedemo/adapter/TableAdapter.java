package com.example.attendancedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.attendancedemo.R;

public class TableAdapter extends BaseAdapter {
    Context context;
    String[] period_name={"ANT","MPMC","TOC"};
    LayoutInflater inflter;
    public TableAdapter(Context applicationContext, String[] period_name) {
        this.context = applicationContext;
        this.period_name= period_name;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return period_name.length;
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
        view = inflter.inflate(R.layout.activity_table, null); // inflate the layout
        EditText editText = (EditText) view.findViewById(R.id.period); // get the reference of ImageView
        editText.setText(period_name[i]);
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}