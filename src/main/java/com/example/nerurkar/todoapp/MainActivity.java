package com.example.nerurkar.todoapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
;


public class MainActivity extends AppCompatActivity {

    EditText DesTxt, DateTxt, TimeTxt;
    CheckBox Reminder;
    ListView lv;
    Spinner Pspinner, Mspinner, Sspinner;
    String PriorityTxt, MainTxt, SubTxt;
    String[] SubCatOptions = {" ", "hey", "hello"};
    Button ColorTheme;
    int M;
    int userID = -10;
    Intent intent;
    boolean insertFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DesTxt = (EditText) findViewById(R.id.des);
        DateTxt = (EditText) findViewById(R.id.date);
        TimeTxt = (EditText) findViewById(R.id.time);
        final Button done = (Button) findViewById(R.id.done_button);
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


        final String[] PriorityOptions = {" ", "High", "Medium", "Low"};
        final String[] MainCatOptions = {" ", "Appointments", "Chores and Errands", "Back to books", "Shopping", "Time for some fun!", "Anything else?"};
        final String[] SubShop = {" ", "Groceries", "Medicines", "Clothes and Accessories", "Gift", "Other"};
        final String[] SubBook = {" ", "Tests", "Assignments", "Library", "Buy books"};
        final String[] SubAppoint = {" ", "Doctor", "Meeting", "Get-together", "Other"};
        final String[] SubChores = {" ", "Pay bills", "Cleaning", "Run to the Gas Station", "Other"};
        final String[] SubFun = {" ", "Travel", "Restaurants", "Amusement Parks"};
        final String[] SubElse = {" "};

        Pspinner = (Spinner) findViewById(R.id.Pspinner);
        Mspinner = (Spinner) findViewById(R.id.Mspinner);
        Sspinner = (Spinner) findViewById(R.id.Sspinner);


        final ArrayAdapter<String> MspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainCatOptions);
        Mspinner.setAdapter(MspinnerAdapter);
        Mspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        M = Mspinner.getSelectedItemPosition();
                        MainTxt = MainCatOptions[M];

                        switch (position) {
                            case 1: {
                                SubCatOptions = SubAppoint;
                                break;
                            }
                            case 2: {
                                SubCatOptions = SubChores;
                                break;
                            }
                            case 3: {
                                SubCatOptions = SubBook;
                                break;
                            }
                            case 4: {
                                SubCatOptions = SubShop;
                                break;
                            }
                            case 5: {
                                SubCatOptions = SubFun;
                                break;
                            }
                            default:
                                SubCatOptions = SubElse;
                        }

                        ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, SubCatOptions);
                        Sspinner.setAdapter(SspinnerAdapter);
                        Sspinner.setEnabled(M != 0);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


        final ArrayAdapter<String> PspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PriorityOptions);
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

        final ArrayAdapter<String> SspinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SubCatOptions);
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
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("creator");
        tabSpec1.setContent(R.id.creator);
        tabSpec1.setIndicator("Add");
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("list");
        tabSpec2.setContent(R.id.list);
        tabSpec2.setIndicator("List");
        tabHost.addTab(tabSpec2);


        final ListView lv = (ListView) findViewById(R.id.listView);
        final DbHandler db = new DbHandler(MainActivity.this);
        setListViewAdapter(lv);
        final ArrayList<HashMap<String, String>> userList = db.GetUsers();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> user = userList.get(position);
                Intent i = new Intent(MainActivity.this, ThingLayout.class);
                i.putExtra("ThingDes", user.get("desc") );
                i.putExtra("ThingDate", user.get("date"));
                i.putExtra("ThingTime", user.get("time"));
                i.putExtra("ThingPriority", user.get("priority"));
                i.putExtra("ThingMain", user.get("main"));
                i.putExtra("ThingSub", user.get("sub"));
                startActivity(i);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                popupMenu.inflate(R.menu.menu_main);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.Delete) {

                            /*String ID = userList.get(position).get("id");
                            ListAdapter adapter = lv.getAdapter();

                            db.DeleteUser(Integer.parseInt(ID));*/

                            Toast.makeText(getApplicationContext(), "Item Deleted!",Toast.LENGTH_LONG).show();
                            return true;
                        }

                        else if (item.getItemId() == R.id.Edit) {
                            //TabHost tabHost =  (TabHost) getParent().findViewById(R.id.tabHost);
                            tabHost.setCurrentTab(0);
                            HashMap<String, String> user = userList.get(position);

                            TimeTxt.setText(user.get("time"));
                            DateTxt.setText(user.get("date"));
                            DesTxt.setText(user.get("desc"));
                            Mspinner.setSelection(MspinnerAdapter.getPosition(user.get("main")));
                            Mspinner.setEnabled(false);
                            Pspinner.setSelection(PspinnerAdapter.getPosition(user.get("priority")));
                            insertFlag = false;
                            userID = Integer.parseInt(user.get("id"));
                            Toast.makeText(getApplicationContext(), "Editting",Toast.LENGTH_LONG).show();
                        }

                        return false;
                    }
                });

                return true;
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHandler dbHandler = new DbHandler(MainActivity.this);

                if(insertFlag){
                dbHandler.insertUserDetails(MainTxt, SubTxt, DesTxt.getText().toString(),
                        DateTxt.getText().toString(), TimeTxt.getText().toString(), PriorityTxt);
                setListViewAdapter(lv);
                Toast.makeText(getApplicationContext(), "Thing added to list!", Toast.LENGTH_SHORT).show();

                }else {
                    dbHandler.UpdateUserDetails(MainTxt, SubTxt, DesTxt.getText().toString(),
                                DateTxt.getText().toString(),  TimeTxt.getText().toString(), PriorityTxt,
                                userID);
                    Mspinner.setEnabled(true);
                    setListViewAdapter(lv);
                    insertFlag = true;
                    userID = -10;
                 }

                TimeTxt.setText("");
                DateTxt.setText("");
                DesTxt.setText("");
                Mspinner.setSelection(0);
                Sspinner.setSelection(0);
                Pspinner.setSelection(0);
            }
        });

    }

    public ListAdapter setListViewAdapter(ListView lv) {
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        final ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList,
                R.layout.listview_item,
                new String[]{"main", "desc"},  //Add SUB later
                new int[]{R.id.rowmain, R.id.rowdes}); //ADD Sub r.id later
        lv.setAdapter(adapter);
        return adapter;
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
