package com.example.project;

public class Post {
    String title;
    String content;
    String date;

    public Post(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}