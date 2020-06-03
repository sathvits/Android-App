package com.example.attendancedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attendancedemo.R;

public class Namelist_Adapter extends BaseAdapter {
    Context context;
    String[] sectionname;
    LayoutInflater inflter;
    public Namelist_Adapter(Context applicationContext, String[] sectionname) {
        this.context = applicationContext;
        this.sectionname = sectionname;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return sectionname.length;
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
        view = inflter.inflate(R.layout.item_name, null); // inflate the layout
        TextView nametext = (TextView) view.findViewById(R.id.name); // get the reference of ImageView
        nametext.setText(sectionname[i]);
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}
