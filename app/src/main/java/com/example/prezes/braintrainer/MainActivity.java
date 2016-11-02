package com.example.prezes.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView go;
    TextView timer;
    CountDownTimer countDownTimer;


    public  void updateTimer (int secondsLeft) {

        int minutes = (int) secondsLeft / 60;  // casting has to round down to 0
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if(secondString == "0") {

            secondString = "00";

        } else if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        timer.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void  go(View view){

        go.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);

        countDownTimer = new CountDownTimer(10 * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timer.setText("0:00");
            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go  = (TextView) findViewById(R.id.goView);
        timer  = (TextView) findViewById(R.id.timerView);
        timer.setVisibility(View.INVISIBLE);
    }
}
