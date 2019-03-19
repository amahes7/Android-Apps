package com.example.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManager dbManager;
    private Button save_buttonn;
    private Button back_button;
    private Button clear_button;
    private EditText question_et, choice1_et, choice2_et, choice3_et, choice4_et;
    private RadioButton rb1, rb2, rb3, rb4;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
        save_buttonn = findViewById(R.id.save);
        back_button = findViewById(R.id.back);
        clear_button = findViewById(R.id.clear);
        save_buttonn.setOnClickListener(this);
        // Retrieve Question and Choices
        question_et = findViewById(R.id.question);
        choice1_et = findViewById(R.id.c1);
        choice2_et = findViewById(R.id.c2);
        choice3_et = findViewById(R.id.c3);
        choice4_et = findViewById(R.id.c4);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        back_button.setOnClickListener(this);
        clear_button.setOnClickListener(this);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.save) {
            insert(view);
        } else if (id == R.id.back) {
            goBack(view);
        } else if (id == R.id.clear) {
            clearAll(view);
        }
    }


    public void insert(View v) {
        String question = question_et.getText().toString();
        String choice1 = choice1_et.getText().toString();
        String choice2 = choice2_et.getText().toString();
        String choice3 = choice3_et.getText().toString();
        String choice4 = choice4_et.getText().toString();
        if (checkForSelection()) {
            Question q = new Question(0, question);
            long qId = dbManager.insertQuestion(q);
            // insert the flag for "iscorrect" by reading the value from radio button
            try {
                int qIdInt = (int) qId;
                Choice c1 = new Choice(0, choice1, rb1.isChecked(), qIdInt);
                Choice c2 = new Choice(0, choice2, rb2.isChecked(), qIdInt);
                Choice c3 = new Choice(0, choice3, rb3.isChecked(), qIdInt);
                Choice c4 = new Choice(0, choice4, rb4.isChecked(), qIdInt);
                dbManager.insertChoice(c1);
                dbManager.insertChoice(c2);
                dbManager.insertChoice(c3);
                dbManager.insertChoice(c4);
                Toast.makeText(this, "Question and choices added", Toast.LENGTH_SHORT).show();
                question_et.setText("Enter Another Question");
                clearRadioButton();
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please select the correct option", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkForSelection() {
        if (rb1.isChecked()) {
            return true;
        } else if (rb2.isChecked()) {
            return true;
        } else if (rb3.isChecked()) {
            return true;
        } else if (rb4.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    public void goBack(View v) {
        Toast.makeText(this, "Going back to the Main Page", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void clearAll(View v) {
        question_et.setText("");
        clearChoices();
        clearRadioButton();
        Toast.makeText(this, "Cleared all fields", Toast.LENGTH_SHORT).show();
    }

    private void clearRadioButton() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }

    private void clearChoices() {
        choice1_et.setText("");
        choice2_et.setText("");
        choice3_et.setText("");
        choice4_et.setText("");
    }
}
