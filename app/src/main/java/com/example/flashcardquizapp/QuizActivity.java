package com.example.flashcardquizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private FlashcardDatabaseHelper db;
    private List<Flashcard> flashcards;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView questionView = findViewById(R.id.tv_question);
        EditText answerInput = findViewById(R.id.et_answer_input);
        Button submitButton = findViewById(R.id.btn_submit_answer);
        TextView progressView = findViewById(R.id.tv_progress);

        db = new FlashcardDatabaseHelper(this);
        flashcards = db.getFlashcardList();
        Collections.shuffle(flashcards);

        if (flashcards.isEmpty()) {
            Toast.makeText(this, "No flashcards available!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        displayQuestion(questionView, progressView);

        submitButton.setOnClickListener(v -> {
            String userAnswer = answerInput.getText().toString().trim();
            if (userAnswer.equalsIgnoreCase(flashcards.get(currentIndex).getAnswer())) {
                score++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong! Correct answer: " + flashcards.get(currentIndex).getAnswer(), Toast.LENGTH_SHORT).show();
            }

            currentIndex++;
            if (currentIndex < flashcards.size()) {
                displayQuestion(questionView, progressView);
                answerInput.setText("");
            } else {
                progressView.setText("Quiz Complete! Your Score: " + score + "/" + flashcards.size());
                submitButton.setEnabled(false);
            }
        });
    }

    private void displayQuestion(TextView questionView, TextView progressView) {
        questionView.setText(flashcards.get(currentIndex).getQuestion());
        progressView.setText("Progress: " + (currentIndex + 1) + "/" + flashcards.size());
    }
}
