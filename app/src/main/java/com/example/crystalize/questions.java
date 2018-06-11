package com.example.crystalize;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class questions extends AppCompatActivity {

    private boolean done = false;
    private int question_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        String[] questions = getResources().getStringArray(R.array.Questions);
        TextView t = findViewById(R.id.question);
        t.setText(questions[question_num]);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
    }

    public void onHintClick(View view) {
        String[] hints  = getResources().getStringArray(R.array.Hints);
        Toast toasty = Toast.makeText(getApplicationContext(), hints[question_num], Toast.LENGTH_SHORT);
        toasty.show();
    }

    public void onAnswerClick(View view) {
        if(!done) {
            String user_answer = ((EditText)findViewById(R.id.answer)).getText().toString().toUpperCase();
            String[] answers = getResources().getStringArray(R.array.Answers);
            String correct_answer = answers[question_num].toUpperCase();

            if(correct_answer.equals(user_answer)) {
                TextView t = findViewById(R.id.correctornot);
                t.setText("CORRECT!");
                ImageView i = findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.weirdtick));
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.Correct_answer);
                mp.start();
                answerSubmitted();
            } else {
                TextView t = findViewById(R.id.correctornot);
                t.setText("CORRECT ANSWER = " + correct_answer);
                ImageView i = findViewById(R.id.tickcross);
                i.setImageDrawable(getDrawable(R.drawable.weirdcross));
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.Wrong_answer);
                mp.start();
                answerSubmitted();
            }
        }
        else {
            done = true;
        }
    }

    public void answerSubmitted() {
        findViewById(R.id.tickcross).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 2000, 0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                findViewById(R.id.correctornot).setVisibility(View.VISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.tickcross).startAnimation(animation);
    }

    public void onNextClick(View view) {
        if(done) {
            String[] questions = getResources().getStringArray(R.array.Questions);
            if(question_num < questions.length - 1) {
                question_num++;
                TextView t = findViewById(R.id.question);
                t.setText(questions[question_num]);

                findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
                findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
                EditText et = findViewById(R.id.answer);
                et.setText("");

                done = false;
            }
        }
    }
}
