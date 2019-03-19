package com.example.helloandroid1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add,subtract,multiply,divide;
    private TextView tvResult,tvResult2;
    private EditText etFirst,etSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        add= findViewById(R.id.add);
        subtract= findViewById(R.id.subtract);
        multiply= findViewById(R.id.multiply);
        divide= findViewById(R.id.divide);
        etFirst= findViewById(R.id.number1);
        etSecond= findViewById(R.id.number2);
        tvResult = findViewById(R.id.result);
        tvResult2 = findViewById(R.id.result2);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        String num1 = etFirst.getText().toString();
        String num2 = etSecond.getText().toString();
        Calculate calc = new Calculate();
        calc.setNum1(Float.parseFloat(num1));
        calc.setNum2(Float.parseFloat(num2));
        tvResult.setText(calc.operation(view.getId()));
        tvResult2.setText(calc.Result(view.getId()));
    }
}
