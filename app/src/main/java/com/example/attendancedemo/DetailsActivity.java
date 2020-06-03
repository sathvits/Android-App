package com.example.attendancedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailsActivity extends AppCompatActivity {

    Button attendance,allocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        attendance = (Button) findViewById(R.id.attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yearIntent = new Intent(DetailsActivity.this, YearActivity.class);
                yearIntent.putExtra("CATEGORY","1");//1=attendance
                startActivity(yearIntent);
            }
        });
        allocate = (Button)findViewById(R.id.allocate);
        allocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yearIntent = new Intent(DetailsActivity.this,YearActivity.class);
                yearIntent.putExtra("CATEGORY","2");//2=class allocated
                startActivity(yearIntent);
            }
        });
    }
}
