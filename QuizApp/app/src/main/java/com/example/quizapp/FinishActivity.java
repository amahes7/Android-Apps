package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FinishActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back_button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        back_button = findViewById(R.id.b_finish);
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.b_finish) {
            Intent mainMenuIntent
                    = new Intent(this, MainActivity.class);
            this.startActivity(mainMenuIntent);
        }
    }
}
