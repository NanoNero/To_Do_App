package com.example.nerurkar.todoapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Nerurkar on 6/15/2015.
 */
public class ThingLayout extends Activity {

    TextView thing_des, thing_date, thing_time, thing_priority,thing_main, thing_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thing_layout);


        thing_des = (TextView)findViewById(R.id.thing_des);
        thing_date = (TextView)findViewById(R.id.thing_date);
        thing_time= (TextView)findViewById(R.id.thing_time);
        thing_priority = (TextView)findViewById(R.id.thing_priority);
        thing_sub = (TextView)findViewById(R.id.thing_sub);
        thing_main = (TextView)findViewById(R.id.thing_main);


        thing_des.setText(getIntent().getExtras().getString("ThingDes"));
        thing_date.setText(getIntent().getExtras().getString("ThingDate"));
        thing_time.setText(getIntent().getExtras().getString("ThingTime"));
        thing_priority.setText(getIntent().getExtras().getString("ThingPriority"));
        thing_main.setText(getIntent().getExtras().getString("ThingMain"));
        thing_sub.setText(getIntent().getExtras().getString("ThingSub"));


        View targetView;
        targetView = (View)findViewById(R.id.relativeLayout1);

        if(thing_priority.getText().equals("High"))
            targetView.setBackgroundColor(Color.parseColor("#EA3335"));

        else if(thing_priority.getText().equals("Medium"))
            targetView.setBackgroundColor(Color.parseColor("#009B72"));

        else {
            targetView.setBackgroundColor(Color.parseColor("#eeea2d"));
        }

    }
}
