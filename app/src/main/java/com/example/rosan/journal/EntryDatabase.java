package com.example.rosan.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/* Created by rosan on 2-3-2018. */

public class EntryDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "entries.db";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_MOOD = "mood";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String TABLE = "EntryTable";
    private static EntryDatabase instance = null;

    // Private constructor (with SQLite)
    private EntryDatabase(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    public static EntryDatabase getInstance(Context context){
        if (instance == null){
            instance = new EntryDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DB = "CREATE TABLE " + TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT NOT NULL, " + KEY_CONTENT + " TEXT NOT NULL, " + KEY_MOOD
                + " TEXT NOT NULL, " + KEY_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP);";
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public Cursor selectAll(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public void insert(JournalEntry entry){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, entry.getTitle());
        values.put(KEY_CONTENT, entry.getContent());
        values.put(KEY_MOOD, entry.getMood());
        sqLiteDatabase.insert(TABLE,null,values);
        sqLiteDatabase.close();
    }

    public void delete(long id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE,KEY_ID + "=" + id,null);
        sqLiteDatabase.close();
    }
}
