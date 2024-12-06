package com.example.flashcardquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FlashcardDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FlashcardDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Flashcards";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_ANSWER = "answer";

    public FlashcardDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_QUESTION + " TEXT," +
                COLUMN_ANSWER + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertFlashcard(String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);
        values.put(COLUMN_ANSWER, answer);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Flashcard> getFlashcardList() {
        List<Flashcard> flashcards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Ensure correct column names
                int questionIndex = cursor.getColumnIndex(COLUMN_QUESTION);
                int answerIndex = cursor.getColumnIndex(COLUMN_ANSWER);

                if (questionIndex != -1 && answerIndex != -1) {
                    String question = cursor.getString(questionIndex);
                    String answer = cursor.getString(answerIndex);
                    flashcards.add(new Flashcard(question, answer));
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return flashcards;
    }

    public List<String> getAllFlashcards() {
        List<String> flashcardList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Ensure correct column names
                int questionIndex = cursor.getColumnIndex(COLUMN_QUESTION);
                int answerIndex = cursor.getColumnIndex(COLUMN_ANSWER);

                if (questionIndex != -1 && answerIndex != -1) {
                    String question = cursor.getString(questionIndex);
                    String answer = cursor.getString(answerIndex);
                    flashcardList.add("Q: " + question + "\nA: " + answer);
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return flashcardList;
    }
}
