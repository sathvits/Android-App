package com.example.attendancedemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.attendancedemo.adapter.HourAdapter;
import com.example.attendancedemo.adapter.ListViewItemCheckboxBaseAdapter;
import com.example.attendancedemo.adapter.ListViewItemDTO;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class NamelistActivity extends AppCompatActivity {
    Webservice ws;
    JSONArray data;
    List<ListViewItemDTO> ret;
    ListView listViewWithCheckbox;
    ListViewItemCheckboxBaseAdapter listViewDataAdapter;
    List<ListViewItemDTO> initItemList;
    Button update;
    String category,year,section;
    String[] period ={"1","2","3","4","5","6","7","8"};
    GridView gridView;
    HourAdapter hourAdapter;
    public String strPeriod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_checkbox);
        gridView = (GridView)findViewById(R.id.gridView);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            category = extras.getString("category");
            year = extras.getString("year");
            section = extras.getString("section");
            //Toast.makeText(getApplicationContext(),"Year"+year + " ~ "+category +" ~ "+section,Toast.LENGTH_LONG).show();
        }

        ws = new Webservice();
        //getList();


        hourAdapter = new HourAdapter(NamelistActivity.this,period);
        gridView.setAdapter(hourAdapter);
        update = (Button)findViewById(R.id.update);

        // Get listview checkbox.
        listViewWithCheckbox = (ListView)findViewById(R.id.list_view_with_checkbox);

        // Initiate listview data.
        // final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();

        // Initiate listview data.
        initItemList = this.getList();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),""+period[position],Toast.LENGTH_LONG).show();
                strPeriod = period[position];
            }
        });



        //getInitViewItemDtoList1();
        // Create a custom list view adapter with checkbox control.
//        final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);
//
//        listViewDataAdapter.notifyDataSetChanged();
//
//        // Set data adapter to list view.
//        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });

        // Click this button to select all listview items with checkbox checked.
        Button selectAllButton = (Button)findViewById(R.id.list_select_all);
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for(int i=0;i<size;i++)
                {
                    ListViewItemDTO dto = initItemList.get(i);
                    dto.setChecked(true);
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to disselect all listview items with checkbox unchecked.
        Button selectNoneButton = (Button)findViewById(R.id.list_select_none);
        selectNoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for(int i=0;i<size;i++)
                {
                    ListViewItemDTO dto = initItemList.get(i);
                    dto.setChecked(false);
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to reverse select listview items.
        Button selectReverseButton = (Button)findViewById(R.id.list_select_reverse);
        selectReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for(int i=0;i<size;i++)
                {
                    ListViewItemDTO dto = initItemList.get(i);

                    if(dto.isChecked())
                    {
                        dto.setChecked(false);
                    }else {
                        dto.setChecked(true);
                    }
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to remove selected items from listview.
        Button selectRemoveButton = (Button)findViewById(R.id.list_remove_selected_rows);
        selectRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(NamelistActivity.this).create();
                alertDialog.setMessage("Are you sure to remove selected listview items?");

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        int size = initItemList.size();
                        for(int i=0;i<size;i++)
                        {
                            ListViewItemDTO dto = initItemList.get(i);

                            if(dto.isChecked())
                            {
                                initItemList.remove(i);
                                i--;
                                size = initItemList.size();
                            }
                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String attendance,today_date;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println(formatter.format(date));
                today_date = formatter.format(date);
                int size = initItemList.size();
                for(int i=0;i<size;i++)
                {
                    ListViewItemDTO dto = initItemList.get(i);
                    if(dto.isChecked())
                    {
                        attendance="1";
                    }else {
                        attendance="0";
                    }
                    System.out.println("Values :: "+dto.getSid() + dto.getSname()+dto.getYear() + dto.getSection()+"PERIOD :: "+strPeriod+today_date +"Attendance ::"+attendance);
                    goToAttendance(dto.getSid() , dto.getSname(),dto.getYear() , dto.getSection(),strPeriod , today_date ,attendance);
                }
            }
        });

    }


    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList()
    {
        String itemTextArr[] = {"Android", "iOS", "Java", "JavaScript", "JDBC", "JSP", "Linux", "Python", "Servlet", "Windows"};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }


    private List<ListViewItemDTO> getList() {
        ret = new ArrayList<ListViewItemDTO>();
        RequestParams param = new RequestParams();
        param.put("section", section);
        param.put("year", year);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Accept", "application/json");
        client.addHeader("Content-Type", "application/json");
        client.get(ws.URL+ws.LIST,param, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                System.out.println("***** OnSuccess ::"+statusCode);
                System.out.println("***** response ::"+response);


                try {
                    JSONObject jsonObj = new JSONObject(response.toString());
                    data = jsonObj.getJSONArray("data");
                    System.out.println("***** Data"+data.length());

                    for(int i=0;i<data.length();i++)
                    {
                        try {

                            JSONObject obj = data.getJSONObject(i);
                            ListViewItemDTO dto = new ListViewItemDTO();
                            dto.setChecked(false);
                            // dto.setItemText("Stud");
                            dto.setSname(obj.getString("name").toString());
                            dto.setSid(obj.getString("id").toString());
                            dto.setYear(obj.getString("year").toString());
                            dto.setSection(obj.getString("section").toString());
                            ret.add(dto);


                        }
                        catch(Exception e){
                            System.out.println("Exception "+e.getMessage());
                        }
                    }

                    listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);

                    listViewDataAdapter.notifyDataSetChanged();

                    // Set data adapter to list view.
                    listViewWithCheckbox.setAdapter(listViewDataAdapter);

//                    String itemTextArr[] = {"Android", "iOS", "Java", "JavaScript", "JDBC", "JSP", "Linux", "Python", "Servlet", "Windows"};
//
//                    List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();
//
//                    int length = itemTextArr.length;
//
//                    for(int i=0;i<length;i++)
//                    {
//                        String itemText = itemTextArr[i];
//
//                        ListViewItemDTO dto = new ListViewItemDTO();
//                        dto.setChecked(false);
//                        dto.setItemText(itemText);
//
//                        ret.add(dto);
//                    }


                }
                catch (Exception e){
                    System.out.println("Exception "+e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println("***** onFailure ::"+statusCode);

            }
        });
        return ret;


    }

    public void goToAttendance(String sid,String sname,String year , String section,String period ,String today_date ,String attendance){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param = new RequestParams();
        param.put("sid",sid);
        param.put("sname", sname);
        param.put("attendance",attendance);
        param.put("section", section);
        param.put("year", year);
        param.put("period", period);
        param.put("date", today_date);
        client.get(ws.URL+ws.ATTENDANCE, param, new AsyncHttpResponseHandler() {
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




}
