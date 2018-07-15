package com.example.nerurkar.todoapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.LocalActivityManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.renderscript.Element;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.app.DatePickerDialog.*;


public class MainActivity extends AppCompatActivity {

    EditText DesTxt, DateTxt, TimeTxt;
    CheckBox Reminder;
    ListView lv;
    Spinner Pspinner, Mspinner, Sspinner;
    String PriorityTxt, MainTxt, SubTxt;
    String[] SubCatOptions = {" ", "hey", "hello"};
    Button ColorTheme;
    int M;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DesTxt = (EditText)findViewById(R.id.des);
        DateTxt = (EditText)findViewById(R.id.date);
        TimeTxt = (EditText)findViewById(R.id.time);
        final Button done = (Button)findViewById(R.id.done_button);
        //ColorTheme = (Button)findViewById(R.id.ColorTheme);
        //Reminder = (CheckBox)findViewById(R.id.Reminder);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        DateTxt.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);

        DateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        int mHour = 0, mMinute = 0;
        final TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        TimeTxt.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);

        TimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpd.show();
            }
        });


        final String[] PriorityOptions = {" ","High","Medium", "Low"};
        final String[] MainCatOptions = {" ","Appointments","Chores and Errands","Back to books","Shopping","Time for some fun!","Anything else?"};
        final String[] SubShop = {" ","Groceries","Medicines","Clothes and Accessories","Gift", "Other"};
        final String[] SubBook = {" ","Tests","Assignments","Library","Buy books"};
        final String[] SubAppoint = {" ","Doctor","Meeting","Get-together", "Other"};
        final String[] SubChores = {" ","Pay bills","Cleaning","Run to the Gas Station", "Other"};
        final String[] SubFun = {" ","Travel","Restaurants","Amusement Parks"};
        final String[] SubElse = {" "};

        Pspinner = (Spinner)findViewById(R.id.Pspinner);
        Mspinner = (Spinner)findViewById(R.id.Mspinner);
        Sspinner = (Spinner)findViewById(R.id.Sspinner);


        ArrayAdapter<String> MspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainCatOptions);
        Mspinner.setAdapter(MspinnerAdapter);
        Mspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        M = Mspinner.getSelectedItemPosition();
                        MainTxt = MainCatOptions[M];


                        switch (position){
                            case 1: {SubCatOptions = SubAppoint;break;}
                            case 2: {SubCatOptions = SubChores;break;}
                            case 3: {SubCatOptions = SubBook;break;}
                            case 4: {SubCatOptions = SubShop;break;}
                            case 5: {SubCatOptions = SubFun;break;}
                            default: SubCatOptions = SubElse;
                        }

                        ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,SubCatOptions);
                        Sspinner.setAdapter(SspinnerAdapter);
                        Sspinner.setEnabled(M != 0);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


        ArrayAdapter<String> PspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PriorityOptions);
        Pspinner.setAdapter(PspinnerAdapter);
        Pspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int p = Pspinner.getSelectedItemPosition();
                        PriorityTxt = PriorityOptions[p];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,SubCatOptions);
        Sspinner.setAdapter(SspinnerAdapter);
        Sspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int p = Sspinner.getSelectedItemPosition();
                        SubTxt = SubCatOptions[p];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("creator");
        tabSpec1.setContent(R.id.creator);
        tabSpec1.setIndicator("Add");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("list");
        tabSpec2.setContent(R.id.list);
        tabSpec2.setIndicator("List");
        tabHost.addTab(tabSpec2);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        ListView lv = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.listview_item,
                new String[]{"main","desc"},  //Add SUB later
                new int[]{R.id.rowmain, R.id.rowdes}); //ADD Sub r.id later
        lv.setAdapter(adapter);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeTxt.setText("");
                DateTxt.setText("");
                DesTxt.setText("");

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(MainTxt, SubTxt, DesTxt.getText().toString(),
                        DateTxt.getText().toString(), TimeTxt.getText().toString(), PriorityTxt);
                //intent = new Intent(MainActivity.this,DetailsActivity.class);
                //startActivity(intent);

                Toast.makeText(getApplicationContext(), "Thing added to list!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

           /* @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }*/
}
