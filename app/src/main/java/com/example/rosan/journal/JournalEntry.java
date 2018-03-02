package com.example.rosan.journal;

import java.io.Serializable;

/** Created by rosan on 2-3-2018. */

public class JournalEntry implements Serializable {
    private int _id;
    public String title;
    private String content;
    private String mood;
    private String timestamp;

    // Constructor
    public JournalEntry(String title, String content, String mood/*, String timestamp*/){
        this.title = title;
        this.content = content;
        this.mood = mood;
        //this.timestamp = timestamp;
    }

    // Getters
    public String getTitle(){
        return title;
    }

    public String getTimestamp(){
        return timestamp;
    }

    public String getContent(){
        return content;
    }

    public String getMood(){
        return mood;
    }

    // Setters
    public void setTitle(String s){
        this.title = s;
    }
}
