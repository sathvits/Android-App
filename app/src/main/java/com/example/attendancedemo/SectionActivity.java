package com.example.attendancedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.attendancedemo.adapter.SectionAdapter;

public class SectionActivity extends AppCompatActivity {
    GridView sectionGrid;
    String[] section_name={"A","B","C"};
    String year,category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_section);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            year = extras.getString("YEAR");
            category = extras.getString("CATEGORY");
           // Toast.makeText(getApplicationContext(),"Year"+year + " ~ "+category,Toast.LENGTH_LONG).show();
        }
        sectionGrid = (GridView) findViewById(R.id.section);

        SectionAdapter sectionAdapter = new SectionAdapter(SectionActivity.this, section_name,category,year);
        sectionGrid.setAdapter(sectionAdapter);
    }
}
