package com.example.laba2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String[] emails;
    private String[] passwords;

    private EditText email;
    private EditText password;

    private boolean isChecked = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Button enter = findViewById(R.id.enter);

        emails = getResources().getStringArray(R.array.emails);
        passwords = getResources().getStringArray(R.array.passwords);

        email.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {

                email.setTextColor(Color.BLACK);

                return true;
            }
            return false;
        });

        password.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {

                password.setTextColor(Color.BLACK);

                if(isChecked)
                {
                    password.setText("");
                    isChecked = false;
                }
                else
                {
                    isChecked = true;
                }

                return true;
            }
            return false;
        });


        enter.setOnClickListener(v -> {
            if(isRightPassword(email.getText().toString(), password.getText().toString()))
            {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
            else
            {
                isChecked = true;
            }
        });
    }

    private boolean isRightPassword(String email, String password)
    {
        int i = 0;

        for (;i < emails.length; i++)
        {
            if (emails[i].equals(email))
            {
                if (passwords[i].equals(password))
                {
                    return true;
                }
                else
                {
                    this.password.setTextColor(Color.RED);
                    return false;
                }
            }
        }

        this.email.setTextColor(Color.RED);
        this.password.setTextColor(Color.RED);
        return false;
    }

}