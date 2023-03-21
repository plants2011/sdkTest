package com.hm.qa.demoapp;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TestInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_input);

        Button backHomeBtn = (Button) findViewById(R.id.backHome);
        backHomeBtn.setOnClickListener(new BackHomeListener());
    }
}