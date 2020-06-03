package com.example.attendancedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendancedemo.NamelistActivity;
import com.example.attendancedemo.R;
import com.example.attendancedemo.SectionActivity;

public class Hourlist_Adapter extends BaseAdapter {
    Context context;
    String[] hourname;
    LayoutInflater inflter;
    Button previousButton=null;
    public Hourlist_Adapter(Activity applicationContext, String[] hourname) {
        this.context = applicationContext;
        this.hourname = hourname;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return hourname.length;
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
        view = inflter.inflate(R.layout.item_hour, null); // inflate the layout
        final Button button = (Button) view.findViewById(R.id.button); // get the reference of ImageView
        button.setText(hourname[i]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context,"Click",Toast.LENGTH_LONG).show();
               /* Intent intent = new Intent(context, NamelistActivity.class);
                context.startActivity(intent); // start*/
               if(previousButton!=null)
                   previousButton.setBackgroundColor(context.getResources().getColor(R.color.colorGrey));

               button.setBackgroundColor(Color.BLACK);

               previousButton=button;
            }
        });
        //icon.setImageResource(logos[i]); // set logo images
        return view;
    }
}
