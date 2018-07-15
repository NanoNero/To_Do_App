package com.example.nerurkar.todoapp;

/**
 * Created by Nerurkar on 7/11/2018.
 */

public class ToDoThing {

    private String name, des, priority, date, time, main, sub;

    public ToDoThing(String des, String priority, String date, String time, String main, String sub){
        this.name = main + " : "+ sub;
        this.des = des;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.main = main;
        this.sub = sub;

    }
    public String GetName(){return this.name;}
    public String GetDes(){return this.des;}
    public String GetDate(){return this.date;}
    public String GetTime(){return this.time;}
    public String GetPriority(){return this.priority;}
    public String GetMain(){return this.main;}
    public String GetSub(){return this.sub;}

    public void SetName(String a){this.name = a;}
    public void SetDes(String a){this.des = a;}
    public void SetDate(String a){this.date = a;}
    public void SetTime(String a){this.time = a;}
    public void SetPriority(String a){this.priority = a;}
    public void SetMain(String a){this.main = a;}
    public void SetSub(String a){this.sub = a;}
}
