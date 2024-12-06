package com.example.flashcardquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "flashcards.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FLASHCARDS = "flashcards";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FLASHCARDS + "(id INTEGER PRIMARY KEY, question TEXT, answer TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLASHCARDS);
        onCreate(db);
    }

    public void addFlashcard(Flashcard flashcard) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", flashcard.getQuestion());
        values.put("answer", flashcard.getAnswer());
        db.insert(TABLE_FLASHCARDS, null, values);
        db.close();
    }

    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FLASHCARDS, null);

        if (cursor.moveToFirst()) {
            do {
                flashcards.add(new Flashcard(cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return flashcards;
    }
}
