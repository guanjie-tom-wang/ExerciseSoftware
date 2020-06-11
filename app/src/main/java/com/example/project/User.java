package com.example.project;

public class User {
    public String username;
    public String TelNumber;
    public String homeAddress;
    public String emailAddress;
    public String password;
    public String type;
    public String friend_request_from;
    public int height;
    public int weight;
    public int age;
    public User(String Un,String TN,String HAD,String ED,String pw,String t, String f,int h,int w,int a){
        username=Un;
        TelNumber=TN;
        homeAddress=HAD;
        emailAddress=ED;
        password=pw;
        friend_request_from=f;
        type=t;
        height=h;
        weight=w;
        age=a;
    }

}
