package com.example.rosan.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/* Created by rosan on 2-3-2018. */

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Retrieve value of one column as integer
        //int value = cursor.getInt(0);
        //int title = cursor.getColumnIndex("title");

        //TextView entryTitle = view.findViewById(R.id.title);


        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndex("title"));
        //entryTitle.setText(title);

        View parent = (View)view.getParent();
        if(parent!=null){
            TextView entryTitle = parent.findViewById(R.id.title);
            entryTitle.setText("title" + title);
        }

        /*view.findViewById(R.id.title);
        view.findViewById(R.id.timestamp);*/

    }
}
