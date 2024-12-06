package com.example.flashcardquizapp;



import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ViewFlashcardsActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flashcards);

        ListView listView = findViewById(R.id.lv_flashcards);
        db = new FlashcardDatabaseHelper(this);

        // Get the flashcards and display them in a ListView
        List<String> flashcards = db.getAllFlashcards();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flashcards);
        listView.setAdapter(adapter);
    }
}
