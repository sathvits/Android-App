package com.example.attendancedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.attendancedemo.adapter.DayAdapter;
import com.example.attendancedemo.adapter.TableAdapter;
import com.example.attendancedemo.adapter.HourAdapter;

public class Table_Activity extends AppCompatActivity {
    GridView tablegrid;
    String[] day_name={"Mon","Tue","Wed","Thu","Fri"};
    String[] hour_name={"1","2","3","4","5","6","7","8"};
    String[] period_name={"ANT","MPMC","TOC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tablegrid = (GridView) findViewById(R.id.period);
        TableAdapter tableadapter = new TableAdapter(Table_Activity.this, period_name);
        tablegrid.setAdapter(tableadapter);

        tablegrid = (GridView) findViewById(R.id.day);
        DayAdapter dayadapter = new DayAdapter(Table_Activity.this, day_name);
        tablegrid.setAdapter(dayadapter);

        tablegrid = (GridView) findViewById(R.id.hours);
        HourAdapter houradapter = new HourAdapter(Table_Activity.this, hour_name);
        tablegrid.setAdapter(houradapter);


    }
}
