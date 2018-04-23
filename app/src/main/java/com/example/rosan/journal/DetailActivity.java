package com.example.rosan.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get details from "previous" activity
        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("clickedEntry");

        // Initiate views
        TextView title = findViewById(R.id.title);
        TextView timestamp = findViewById(R.id.timestamp);
        TextView content = findViewById(R.id.content);
        ImageView mood = findViewById(R.id.mood);

        // Set title, timestamp and content
        title.setText(entry.getTitle());
        timestamp.setText(entry.getTimestamp());
        content.setText(entry.getContent());

        // set image according to mood
        int id = this.getResources().getIdentifier(entry.getMood(), "drawable", this.getPackageName());
        mood.setImageResource(id);

    }
}
