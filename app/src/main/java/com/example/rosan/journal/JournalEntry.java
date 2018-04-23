package com.example.rosan.journal;

import java.io.Serializable;

/** Created by rosan on 2-3-2018. */

public class JournalEntry implements Serializable {
    public int _id;
    public String title;
    public String content;
    public String mood;
    public String timestamp;

    // Constructor
    public JournalEntry(String title, String content, String mood/*, String timestamp*/){
        this.title = title;
        this.content = content;
        this.mood = mood;
        //this.timestamp = timestamp;
    }

    public JournalEntry(Integer id, String title, String content, String mood, String timestamp){
        this._id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
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

    public void setTimestamp(String s) {this.timestamp = s; }
}
