package com.rocdev.android.braintrainerapp1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int highScore;
    TextView highScoreTextView;
    TextView newHighTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        highScore = prefs.getInt("highScore",0);

        highScoreTextView = (TextView)findViewById(R.id.highScoreTextView);
        newHighTextView = (TextView)findViewById(R.id.newHighTextView);
        newHighTextView.setText("");

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);

        if (score > highScore){
            highScore = score;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", highScore);
            editor.commit();
            newHighTextView.setText("Nieuwe high score, awesome!");
        }

        highScoreTextView.setText("High score: " + highScore);

    }

   public void start(View view){
        Intent intent = new Intent(this, TimerActivity.class);
       startActivity(intent);
   }
}
