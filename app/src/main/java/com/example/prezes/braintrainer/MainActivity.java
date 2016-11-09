package com.example.prezes.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.prezes.braintrainer.R.id.resultTextView;

public class MainActivity extends AppCompatActivity {

    TextView timerView;
    TextView sumView;
    TextView pointsView;
    TextView resultTextView;
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Random random;


    public void generateQuestion (){

        random = new Random();
        int a = random.nextInt(21); //20
        int b = random.nextInt(21); //20

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear(); // has to be cleared every run of generateQuestion(); otherwise button view won't update

        int incorrectAnswer;

        sumView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b); //adding correct answer in random button
            } else {

                incorrectAnswer = random.nextInt(41); // 41 because it is a total of both a&b(20 for each)

                while (incorrectAnswer == a + b) { // while created because can be equal to the user answer
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer); // sum of total // potential issue: this result can be equal to the answer
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong");
        }

        numberOfQuestions++;
        pointsView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }
    public void updateTimer (int secondsLeft) {

        int minutes = (int) secondsLeft / 60;  // casting has to round down to 0
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if(secondString == "0") {

            secondString = "00";

        } else if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        timerView.setText(Integer.toString(minutes) + ":" + secondString);
    }
    public void startMethod(){

        startButton.setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(30 * 1000 + 100/*30100*/, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timerView.setText("0:00");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView  = (TextView) findViewById(R.id.timerView);
        sumView  = (TextView) findViewById(R.id.sumView);
        pointsView  = (TextView) findViewById(R.id.pointsView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        startButton = (Button) findViewById(R.id.startButton);
        button0 = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        startMethod();
        generateQuestion();

    }
}
