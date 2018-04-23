package com.example.rosan.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Timestamp;

/* Created by rosan on 2-3-2018. */

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String timestamp = (Timestamp.valueOf(cursor.getString(4))).toString();

        // Set title and date
        TextView entryTitle = view.findViewById(R.id.title);
        entryTitle.setText(title);

        TextView timeStamp = view.findViewById(R.id.timestamp);
        timeStamp.setText(timestamp);

    }
}
