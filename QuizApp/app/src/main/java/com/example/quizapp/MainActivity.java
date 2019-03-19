package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private Button start_button;
    private ArrayList<Question> questionArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
        scrollView = findViewById(R.id.scrollView);
        start_button = findViewById(R.id.start_button);
        start_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.start_button) {
            questionArray = dbManager.selectAll();
            if (questionArray.size() > 0) {
                Intent displayIntent
                        = new Intent(this, QuizDisplay.class);
                this.startActivity(displayIntent);
            } else {
                Toast.makeText(this, "Kindly Add A Question First!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                Intent insertIntent
                        = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                questionArray = dbManager.selectAll();
                if (questionArray.size() > 0) {
                    Intent deleteIntent
                            = new Intent(this, DeleteActivity.class);
                    this.startActivity(deleteIntent);
                } else {
                    Toast.makeText(this, "Kindly Add A Question First!!", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onResume() {
        super.onResume();
    }
}