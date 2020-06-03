package com.example.attendancedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class YearActivity extends AppCompatActivity {

    Button first,second,third,fourth;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_year);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            category = extras.getString("CATEGORY");
            //Toast.makeText(getApplicationContext(),"Value"+category,Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.item_year);
        first=(Button)findViewById(R.id.first);
        second=(Button)findViewById(R.id.second);
        third=(Button)findViewById(R.id.third);
        fourth=(Button)findViewById(R.id.fourth);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YearActivity.this,SectionActivity.class);
                intent.putExtra("CATEGORY",category);
                intent.putExtra("YEAR","1");
                startActivity(intent);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YearActivity.this,SectionActivity.class);
                intent.putExtra("CATEGORY",category);
                intent.putExtra("YEAR","2");
                startActivity(intent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YearActivity.this,SectionActivity.class);
                intent.putExtra("CATEGORY",category);
                intent.putExtra("YEAR","3");
                startActivity(intent);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(YearActivity.this,SectionActivity.class);
                intent.putExtra("CATEGORY",category);
                intent.putExtra("YEAR","4");
                startActivity(intent);
            }
        });


    }
}
