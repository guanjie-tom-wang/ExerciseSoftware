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
    public User(String Un,String TN,String HAD,String ED,String pw,String t, ArrayList<String> fq, ArrayList<String> fl,int h,int w,int a){
        username=Un;
        TelNumber=TN;
        homeAddress=HAD;
        emailAddress=ED;
        password=pw;
        friend_request=fq;
        friend_list=fl;
        type=t;
        height=h;
        weight=w;
        age=a;
    }

}
