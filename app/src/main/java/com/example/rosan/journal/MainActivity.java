package com.example.rosan.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate views
        listView = findViewById(R.id.listView);

        // Create database
        db = EntryDatabase.getInstance(getApplicationContext());

        // Set listeners
        listView.setOnItemClickListener(new displayEntry());
        listView.setOnItemLongClickListener(new deleteEntry());

        loadEntryList();
    }

    private void loadEntryList() {

        // Get data from database and set in adapter to show in activity
        EntryAdapter adapter = new EntryAdapter(MainActivity.this, db.selectAll());
        listView.setAdapter(adapter);
    }

    public void addEntry(View view) {

        // Go to InputActivity
        Intent intent = new Intent(MainActivity.this,InputActivity.class);
        startActivity(intent);
    }

    private class displayEntry implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View v, int i, long l){

            Cursor cursor = (Cursor) adapterView.getAdapter().getItem(i);

            // Extract id, title, content, mood, timestamp and make an JournalEntry
            Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = (Timestamp.valueOf(cursor.getString(4))).toString();
            JournalEntry clickedEntry = new JournalEntry(id,title,content,mood,timestamp);

            // Start new activity
            Intent intent = new Intent (MainActivity.this, DetailActivity.class);
            intent.putExtra("clickedEntry", clickedEntry);
            startActivity(intent);
        }
    }

    private class deleteEntry implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long id) {

            // Delete selected JournalEntry by id
            db.delete(id);

            // Notify the user
            Toast.makeText(MainActivity.this, "entry deleted", Toast.LENGTH_SHORT).show();
            loadEntryList();
            return true;
        }
    }
}
