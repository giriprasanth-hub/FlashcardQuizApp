package com.example.flashcardquizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddFlashcardActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flashcard);

        EditText questionInput = findViewById(R.id.et_question);
        EditText answerInput = findViewById(R.id.et_answer);
        Button saveButton = findViewById(R.id.btn_save_flashcard);

        db = new FlashcardDatabaseHelper(this);

        saveButton.setOnClickListener(v -> {
            String question = questionInput.getText().toString().trim();
            String answer = answerInput.getText().toString().trim();

            if (question.isEmpty() || answer.isEmpty()) {
                Toast.makeText(this, "Both fields are required!", Toast.LENGTH_SHORT).show();
            } else {
                db.insertFlashcard(question, answer);
                Toast.makeText(this, "Flashcard saved!", Toast.LENGTH_SHORT).show();
                questionInput.setText("");
                answerInput.setText("");
            }
        });
    }
}
