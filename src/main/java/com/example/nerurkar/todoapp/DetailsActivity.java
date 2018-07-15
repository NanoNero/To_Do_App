package com.example.nerurkar.todoapp;

/**
 * Created by Nerurkar on 7/14/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity{

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();

        ListView lv = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this, userList,
                R.layout.listview_item,
                new String[]{"main","desc"},  //Add SUB later
                new int[]{R.id.rowmain, R.id.rowdes}); //ADD Sub r.id later
        lv.setAdapter(adapter);*/

        /*Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
                //intent = new Intent(DetailsActivity.this, MainActivity.class);
                //startActivity(intent);
           /* }
        });*/

           //Add listview onitemclick listener, and on long press listener
    }
}
