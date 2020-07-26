package com.example.project;

import java.util.ArrayList;

public class User {
    public String username;
    public String TelNumber;
    public String homeAddress;
    public String emailAddress;
    public String password;
    public String type;
    public ArrayList<String> friend_request;
    public ArrayList<String> friend_list;
    public int height;
    public int weight;
    public int age;
    public int stepnumber;
    public User(String username,String TelNumber,String homeAddress,String emailAddress,String password,String type, ArrayList<String> friend_request, ArrayList<String> friend_list,int height,int weight,int age,int stepnumber){
        this.username=username;
        this.TelNumber=TelNumber;
        this.homeAddress=homeAddress;
        this.emailAddress=emailAddress;
        this.password=password;
        this.friend_request=friend_request;
        this.friend_list=friend_list;
        this. type=type;
        this.height=height;
        this.weight=weight;
        this.age=age;
        this.stepnumber=stepnumber;
    }

}
