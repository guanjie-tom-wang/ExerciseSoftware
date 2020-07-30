package com.example.project;

public class PostFromCoach {
    String title;
    String content;
    int day;
    int month;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    int year;

    public PostFromCoach(String title, String content, int day, int month, int year) {
        this.title = title;
        this.content = content;
        this.day = day;
        this.month = month;
        this.year = year;
    }



}