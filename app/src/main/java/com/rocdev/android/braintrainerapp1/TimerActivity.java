package com.rocdev.android.braintrainerapp1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class TimerActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView scoreTextView;
    private Button[] buttons;
    private int correctAnswer;
    private int numberCorrect;
    private int numberTries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        buttons = new Button[4];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
        }
        numberCorrect = 0;
        numberTries = 0;
        startTimer();
        maakSom();


    }

    private void startTimer() {
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("score", numberCorrect);
                startActivity(intent);

            }
        }.start();

    }

    private void maakSom() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        correctAnswer = a + b;

        int correctAnswerButton = rand.nextInt(4);
        buttons[correctAnswerButton].setText(String.valueOf(correctAnswer));
        for (int i = 0; i < buttons.length; i++) {
            if (i != correctAnswerButton) {
                int wrongAnswer = rand.nextInt(21);
                while (wrongAnswer == correctAnswer) {
                    wrongAnswer = rand.nextInt(21);

                }
                buttons[i].setText(String.valueOf(wrongAnswer));

            }
        }
    }

        public void chooseAnswer(View view){
            numberTries++;
            Button button = (Button) view;
            int answer = Integer.parseInt(button.getText().toString());
            if(answer == correctAnswer){
                numberCorrect++;
            }

            scoreTextView.setText(String.valueOf(numberCorrect) + "/" + numberTries);
            maakSom();
    }
}


