package com.hm.qa.demoapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TestLandscape extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_landscape);

        Button backHomeBtn = (Button) findViewById(R.id.backHome);
        backHomeBtn.setOnClickListener(new BackHomeListener());
    }
}