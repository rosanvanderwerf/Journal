package com.example.rosan.journal;

import android.content.Intent;
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

    //ArrayList<JournalEntry> entries;
    EntryDatabase db;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);

         db = EntryDatabase.getInstance(getApplicationContext());

        listView.setOnItemClickListener(new displayEntry());
        listView.setOnItemLongClickListener(new deleteEntry());

        JournalEntry entry1 = new JournalEntry("example", "this is an example", "shocked");
        Toast.makeText(this, entry1.getTitle(), Toast.LENGTH_SHORT).show();

        db.insert(entry1);
        loadEntryList();


    }

    private void loadEntryList() {
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
            // Go to DetailActivity
            Intent intent = new Intent(MainActivity.this,DetailActivity.class);
            // PutExtra Entry, to new intent
            startActivity(intent);
        }
    }

    private class deleteEntry implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long id) {
            EntryAdapter adapter = new EntryAdapter(MainActivity.this, db.selectAll() );
            // getItem? to extract which entry it is about
            // Delete Entry

            loadEntryList();
            return true;
        }
    }


}
