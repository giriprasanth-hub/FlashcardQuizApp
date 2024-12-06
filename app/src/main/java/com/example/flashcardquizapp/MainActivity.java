package com.example.flashcardquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addFlashcardButton = findViewById(R.id.btn_add_flashcard);
        Button quizButton = findViewById(R.id.btn_quiz);
        Button viewFlashcardsButton = findViewById(R.id.btn_view_flashcards);
        Button aboutButton = findViewById(R.id.btn_about);

        // Navigate to AddFlashcardActivity
        addFlashcardButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddFlashcardActivity.class);
            startActivity(intent);
        });

        // Navigate to QuizActivity
        quizButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        // Navigate to ViewFlashcardsActivity
        viewFlashcardsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewFlashcardsActivity.class);
            startActivity(intent);
        });

        // Show About Dialog
        aboutButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("About");
            builder.setMessage("Flashcard Quiz App\n\nMade by Giriprasanth");
            builder.setPositiveButton("OK", null);
            builder.show();
        });
    }
}
