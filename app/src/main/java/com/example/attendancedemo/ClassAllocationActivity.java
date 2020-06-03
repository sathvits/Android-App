package com.example.attendancedemo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.attendancedemo.adapter.GridViewCheckBoxAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ClassAllocationActivity extends AppCompatActivity {
    GridView gridView;
    List<ClassAllocateValues> initItemList;
    GridViewCheckBoxAdapter gridViewDataAdapter;
    Button update;
    Webservice ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_allocation);

        ws = new Webservice();
        gridView = (GridView)findViewById(R.id.gridView);
        update = (Button)findViewById(R.id.update);
        initItemList = this.getInitViewItemDtoList();
        gridViewDataAdapter = new GridViewCheckBoxAdapter(getApplicationContext(), initItemList);

        gridViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        gridView.setAdapter(gridViewDataAdapter);

        // When list view item is clicked.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                String staffid="1";
                String year = "1";
                String section = "B";
                String day="";
                int period=0;
                String classstatus="";

                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ClassAllocateValues itemDto = (ClassAllocateValues)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                    classstatus = "No";

                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                    classstatus = "Yes";

                }
                period = itemIndex%8;
                period = period+1;


                if(itemIndex<=7) {
                    day = "Mon";
                    // period = i+1;

                }
                else if(itemIndex>7 && itemIndex<=15) {
                    day = "Tue";
                    //period = i%8;
                }
                else if(itemIndex>15 && itemIndex<=23) {
                    day = "Wed";
                    //  period = i%8;
                }
                else if(itemIndex>23 && itemIndex<=31) {
                    day = "Thu";
                    // period = i%8;
                }
                else if(itemIndex>31 && itemIndex<=39) {
                    day = "Fri";
                    // period = i%8;
                }

                Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
                goToAllocate(staffid , year,section,day,period,classstatus);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startAlert();
                String staffid="1";
                String year = "1";
                String section = "B";
                String day="";
                int period=0;
                String attendance,today_date;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println(formatter.format(date));
                today_date = formatter.format(date);
                int size = initItemList.size();
                for(int i=0;i<size;i++)
                {
                    ClassAllocateValues dto = initItemList.get(i);

                    if(i<=7) {
                        day = "Mon";
                       // period = i+1;

                    }
                    else if(i>7 && i<=15) {
                        day = "Tue";
                        //period = i%8;
                    }
                    else if(i>15 && i<=23) {
                        day = "Wed";
                      //  period = i%8;
                    }
                    else if(i>23 && i<=31) {
                        day = "Thu";
                       // period = i%8;
                    }
                    else if(i>31 && i<=39) {
                        day = "Fri";
                       // period = i%8;
                    }



                    if(dto.isChecked())
                    {
                        //attendance="1";
                        period = i%8;
                    }else {
                       // attendance="0";
                    }

                    System.out.println("Period :: "+period);
                    System.out.println("Day :: "+day);

                  goToAllocate(staffid , year,section,day,period,"0");
                }
            }
        });
    }

    // Return an initialize list of ListViewItemDTO.
    private List<ClassAllocateValues> getInitViewItemDtoList()
    {
        String itemTextArr[] = {"1", "2", "3", "4", "5", "6", "7", "8",
                "9", "10","11","12","13","14","15","16",
                "17", "18","19","20","21","22","23","24",
                "25", "26","27","28","29","30","31","32",
                "33","34","35","36","37","38","39","40"};

        List<ClassAllocateValues> ret = new ArrayList<ClassAllocateValues>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ClassAllocateValues dto = new ClassAllocateValues();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

    public void goToAllocate(String staffid , String year, String section, String day, int period,String classstatus){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param = new RequestParams();
        param.put("staffid",staffid);
        param.put("year", year);
        param.put("section",section);
        param.put("day", day);
        param.put("period", period);
        param.put("classallo", classstatus);


        client.get(ws.URL+ws.CLASSALLOCATE, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(getApplicationContext(),"Success "+statusCode,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"Error"+statusCode,Toast.LENGTH_LONG).show();
            }
        });
    }

   /* public void startAlert(){
        //  EditText text = findViewById(R.id.time);
        int i = 2;
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
        //       + (i * 1000), pendingIntent);
        //  Toast.makeText(this, "Alarm set in " + i + " seconds",Toast.LENGTH_LONG).show();

        Calendar alarmCalendar= Calendar.getInstance();
        alarmCalendar.set(Calendar.DAY_OF_WEEK, 3);

        alarmCalendar.set(Calendar.HOUR, 2);
        alarmCalendar.set(Calendar.MINUTE, 05);
        alarmCalendar.set(Calendar.SECOND, 0);
        alarmCalendar.set(Calendar.AM_PM, Calendar.PM);

        Long alarmTime = alarmCalendar.getTimeInMillis();
        //Also change the time to 24 hours.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, 24 * 60 * 60 * 1000 , pendingIntent);
    }*/
}

