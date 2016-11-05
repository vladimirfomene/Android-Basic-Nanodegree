package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* Variable keeps track of team A's score */
    private int scoreTeamA = 0;

    /* Variable keeps track of team B's score */
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Adds two points to our score board for team A
     */
    public void plusTwoForTeamA(View view){
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Adds two points to our score board for team A
     */
    public void plusTwoForTeamB(View view){
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Adds three points to our score board for team A
     */
    public void plusThreeForTeamA(View view){
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Adds three points to our score board for team B
     */
    public void plusThreeForTeamB(View view){
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Adds one point to the score board for team A
     */
    public void freeThrowForTeamA(View view){
        displayForTeamA(++scoreTeamA);
    }

    /**
     * Adds one point to the score board for team B
     */
    public void freeThrowForTeamB(View view){
        displayForTeamB(++scoreTeamB);
    }


    /**
     * Resets both team scores to Zero
     */
    public void reset(View view){
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }
}
