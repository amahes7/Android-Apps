package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizDisplay extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManager dbManager;
    private Button p_button;
    private Button n_button;
    private Button back_button;
    private Button result_button;
    private TextView question_et_d, choice1_et_d, choice2_et_d, choice3_et_d, choice4_et_d, result_tv;
    private RadioButton rb1_d, rb2_d, rb3_d, rb4_d;
    private int questionCount, correctOption, selectedOption, arrayCount, score = 0;
    private ArrayList<Question> questionArray;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_display);
        questionArray = dbManager.selectAll();
        result_button = findViewById(R.id.result_button);
        result_button.setOnClickListener(this);
        p_button = findViewById(R.id.previous);
        n_button = findViewById(R.id.next);
        p_button.setOnClickListener(this);
        n_button.setOnClickListener(this);
        question_et_d = findViewById(R.id.question_display);
        choice1_et_d = findViewById(R.id.c1_display);
        choice2_et_d = findViewById(R.id.c2_display);
        choice3_et_d = findViewById(R.id.c3_display);
        choice4_et_d = findViewById(R.id.c4_display);
        result_tv = findViewById(R.id.score_tv);
        rb1_d = findViewById(R.id.rb1_display);
        rb2_d = findViewById(R.id.rb2_display);
        rb3_d = findViewById(R.id.rb3_display);
        rb4_d = findViewById(R.id.rb4_display);
        rb1_d.setOnClickListener(this);
        rb2_d.setOnClickListener(this);
        rb3_d.setOnClickListener(this);
        rb4_d.setOnClickListener(this);
        back_button = findViewById(R.id.b_button);
        back_button.setOnClickListener(this);
        selectedOption = 1;
        correctOption = 1;
        questionCount = 0;
        arrayCount = 0;
        result_button.setEnabled(false);
        displayOnFirstLoad();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.previous) {
            previousQuestion();
        } else if (id == R.id.next) {
            nextQuestion();
        } else if (id == R.id.b_button) {
            goBack(view);
        } else if (id == R.id.rb1_display) {
            selectedOption = 1;
            clearSelectionForRadioButton();
            CheckCorrectOption();
        } else if (id == R.id.rb2_display) {
            selectedOption = 2;
            clearSelectionForRadioButton();
            CheckCorrectOption();
        } else if (id == R.id.rb3_display) {
            selectedOption = 3;
            clearSelectionForRadioButton();
            CheckCorrectOption();
        } else if (id == R.id.rb4_display) {
            selectedOption = 4;
            clearSelectionForRadioButton();
            CheckCorrectOption();
        } else if (id == R.id.b_button) {
            Intent mainMenuIntent
                    = new Intent(this, MainActivity.class);
            this.startActivity(mainMenuIntent);
        } else if (id == R.id.result_button) {
            setScore();
        }
    }

    public void setScore() {
        result_tv.setText(Integer.toString(score));
    }

    public void goBack(View v) {
        Toast.makeText(this, "Going back to the Main Menu", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void displayOnFirstLoad() {
        p_button.setEnabled(false);
        if (questionArray.size() > 0) {
            question_et_d.setText(questionArray.get(arrayCount).getQuestion());
            questionCount = 1;
            ArrayList<Choice> choiceArray = dbManager.selectAllChoiceById(questionArray.get(arrayCount).getId());
            choice1_et_d.setText(choiceArray.get(0).getChoice());
            choice2_et_d.setText(choiceArray.get(1).getChoice());
            choice3_et_d.setText(choiceArray.get(2).getChoice());
            choice4_et_d.setText(choiceArray.get(3).getChoice());
            correctOption = identifyCorrectOption(choiceArray);
        }
    }

    private void CheckCorrectOption() {
        if (correctOption == selectedOption) {
            score = score + 1;
            Toast.makeText(this, "Correct Option", Toast.LENGTH_SHORT).show();
            selectCorrectOption();
        } else {
            selectIncorrectOption();
            Toast.makeText(this, "InCorrect Option", Toast.LENGTH_SHORT).show();
        }

    }

    private void selectIncorrectOption() {
        if (selectedOption == 1) {
            rb1_d.setChecked(true);
        } else if (selectedOption == 2) {
            rb2_d.setChecked(true);
        } else if (selectedOption == 3) {
            rb3_d.setChecked(true);
        } else if (selectedOption == 4) {
            rb4_d.setChecked(true);
        }
    }

    private void selectCorrectOption() {
        if (correctOption == 1) {
            rb1_d.setChecked(true);
        } else if (correctOption == 2) {
            rb2_d.setChecked(true);
        } else if (correctOption == 3) {
            rb3_d.setChecked(true);
        } else if (correctOption == 4) {
            rb4_d.setChecked(true);
        }
    }

    private void clearSelectionForRadioButton() {
        rb1_d.setChecked(false);
        rb3_d.setChecked(false);
        rb2_d.setChecked(false);
        rb4_d.setChecked(false);
    }

    private int identifyCorrectOption(ArrayList<Choice> choiceArray) {
        int flag = 1;
        if (choiceArray.get(0).getIsCorrect()) {
            flag = 1;
        } else if (choiceArray.get(1).getIsCorrect()) {
            flag = 2;
        } else if (choiceArray.get(2).getIsCorrect()) {
            flag = 3;
        } else if (choiceArray.get(3).getIsCorrect()) {
            flag = 4;
        }
        return flag;
    }

    private void nextQuestion() {
        questionCount++;
        arrayCount++;
        clearSelectionForRadioButton();
        if (questionArray.size() > questionCount) {
            p_button.setEnabled(true);
            question_et_d.setText(questionArray.get(arrayCount).getQuestion());
            ArrayList<Choice> choiceArray = dbManager.selectAllChoiceById(questionArray.get(arrayCount).getId());
            choice1_et_d.setText(choiceArray.get(0).getChoice());
            choice2_et_d.setText(choiceArray.get(1).getChoice());
            choice3_et_d.setText(choiceArray.get(2).getChoice());
            choice4_et_d.setText(choiceArray.get(3).getChoice());
            correctOption = identifyCorrectOption(choiceArray);
        } else if (questionArray.size() == questionCount) {
            p_button.setEnabled(true);
            question_et_d.setText(questionArray.get(arrayCount).getQuestion());
            ArrayList<Choice> choiceArray = dbManager.selectAllChoiceById(questionArray.get(arrayCount).getId());
            choice1_et_d.setText(choiceArray.get(0).getChoice());
            choice2_et_d.setText(choiceArray.get(1).getChoice());
            choice3_et_d.setText(choiceArray.get(2).getChoice());
            choice4_et_d.setText(choiceArray.get(3).getChoice());
            correctOption = identifyCorrectOption(choiceArray);
            result_button.setEnabled(true);
        } else {
            questionCount--;
            arrayCount--;
            n_button.setEnabled(false);
            Toast.makeText(this, "End OF Quiz!!", Toast.LENGTH_SHORT).show();
            Intent finalIntent
                    = new Intent(this, FinishActivity.class);
            this.startActivity(finalIntent);
        }
    }

    private void previousQuestion() {
        questionCount--;
        arrayCount--;
        result_button.setEnabled(false);
        clearSelectionForRadioButton();
        n_button.setEnabled(true);
        if (questionCount == 1) {
            question_et_d.setText(questionArray.get(arrayCount).getQuestion());
            ArrayList<Choice> choiceArray = dbManager.selectAllChoiceById(questionArray.get(arrayCount).getId());
            choice1_et_d.setText(choiceArray.get(0).getChoice());
            choice2_et_d.setText(choiceArray.get(1).getChoice());
            choice3_et_d.setText(choiceArray.get(2).getChoice());
            choice4_et_d.setText(choiceArray.get(3).getChoice());
            correctOption = identifyCorrectOption(choiceArray);
            p_button.setEnabled(false);
            Toast.makeText(this, "Reached Question 1!! ", Toast.LENGTH_SHORT).show();
        } else if (questionArray.size() >= questionCount) {
            question_et_d.setText(questionArray.get(arrayCount).getQuestion());
            ArrayList<Choice> choiceArray = dbManager.selectAllChoiceById(questionArray.get(arrayCount).getId());
            choice1_et_d.setText(choiceArray.get(0).getChoice());
            choice2_et_d.setText(choiceArray.get(1).getChoice());
            choice3_et_d.setText(choiceArray.get(2).getChoice());
            choice4_et_d.setText(choiceArray.get(3).getChoice());
            correctOption = identifyCorrectOption(choiceArray);
        }
    }
}