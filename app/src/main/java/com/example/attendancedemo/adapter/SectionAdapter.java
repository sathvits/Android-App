package com.example.attendancedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendancedemo.ClassAllocationActivity;
import com.example.attendancedemo.NamelistActivity;
import com.example.attendancedemo.R;
import com.example.attendancedemo.SectionActivity;

public class SectionAdapter extends BaseAdapter {
    Context context;
    String[] sectionname;
    LayoutInflater inflter;
    String category;
    String year;
    String section ;
    public SectionAdapter(Activity applicationContext, String[] sectionname,String category,String year) {
        this.context = applicationContext;
        this.sectionname = sectionname;
        this.year = year;
        this.category = category;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.item_section, null); // inflate the layout
        Button button = (Button) view.findViewById(R.id.button); // get the reference of ImageView
        button.setText(sectionname[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                section = sectionname[i];
               // Toast.makeText(context,"Click",Toast.LENGTH_LONG).show();
                if(category.equals("1")) {
                    Intent intent = new Intent(context, NamelistActivity.class);
                    intent.putExtra("category", category);
                    intent.putExtra("year", year);
                    intent.putExtra("section", section);
                    context.startActivity(intent); // start
                }
                else
                {
                    Intent intent = new Intent(context, ClassAllocationActivity.class);
                    intent.putExtra("category", category);
                    intent.putExtra("year", year);
                    intent.putExtra("section", section);
                    context.startActivity(intent); // start
                }
            }
        });
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}
