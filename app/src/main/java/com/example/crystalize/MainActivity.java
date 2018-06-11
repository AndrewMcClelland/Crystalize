package com.example.crystalize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page); // where it references the XML layout file
    }

    public void onSplashPageClick(View view) {
        Intent intent = new Intent(this, questions.class);
        startActivity(intent); // start 'Questions' activity when screen clicked
        finish(); // closes current activity (splash_page) (now clicking 'back' from the questions activity
    }
}
