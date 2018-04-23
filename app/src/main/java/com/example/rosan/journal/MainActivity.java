package com.example.rosan.journal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ListView;
import android.widget.Toast;

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

        }
    }

    private class deleteEntry implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long id) {
            EntryAdapter adapter = new EntryAdapter(MainActivity.this, db.selectAll() );

            // Delete selected JournalEntry by id
            db.delete(id);

            // Notify the user
            Toast.makeText(MainActivity.this, "entry deleted", Toast.LENGTH_SHORT).show();
            loadEntryList();
            return true;
        }
    }
}
