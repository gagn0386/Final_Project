package com.example.finalproject;

import java.net.URL;

public class Article {

    //This class represents an instance of our News Articles
    //1 Article object equals 1 News Article

    //Adding the attributes of Article
    public String title;
    public String description;
    public String date;
    public String link;


    //Getter and Setter methods for our Article Objects

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
