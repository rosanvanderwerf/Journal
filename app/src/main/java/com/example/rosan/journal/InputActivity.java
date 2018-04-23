package com.example.rosan.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class InputActivity extends AppCompatActivity {

    EntryDatabase db;
    String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        db = EntryDatabase.getInstance(getApplicationContext());
    }

    public void addEntry(View view) {

        // Find strings that should be stored in the database (title, date, happy, mood)
        TextView entryTitle = findViewById(R.id.title);
        TextView entryContent = findViewById(R.id.content);
                String title = entryTitle.getText().toString();
        String content = entryContent.getText().toString();
        String Mood = mood;

        if(title.length() <= 0 || content.length() <= 0 /*|| timestamp.length()<=0*/ || Mood==null){
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Add entry to database
            JournalEntry entry = new JournalEntry(title,content,Mood/*,timestamp*/);
            db.insert(entry);

            // Notify user
            Toast.makeText(this, "Entry is added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InputActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    public void happySelected(View view) { mood = "happy"; }

    public void shockedSelected(View view) { mood = "shocked"; }

    public void madSelected(View view) { mood = "mad"; }

    public void sadSelected(View view) { mood = "sad"; }

    public void cancelEntry(View view) {
        Intent intent = new Intent(InputActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
