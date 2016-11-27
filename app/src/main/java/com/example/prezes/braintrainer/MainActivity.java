package com.example.prezes.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerView;
    TextView sumView;
    TextView pointsView;
    TextView resultTextView;
    RelativeLayout GameRelativeLayout;
    Button startButtonAdd;
    Button startButtonMultiplication;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button newAddGameButton;
    Button newMultiplicationGameButton;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Random random;

    public void playAddAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        timerView.setText("30s");
        pointsView.setText("0/0");
        resultTextView.setText("");
        newAddGameButton.setVisibility(View.INVISIBLE);
        newMultiplicationGameButton.setVisibility(View.INVISIBLE);

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        timerView.setVisibility(View.VISIBLE);
        sumView.setVisibility(View.VISIBLE);
        pointsView.setVisibility(View.VISIBLE);

        generateAddQuestion();

        startMethod();
    }

    public void generateAddQuestion(){

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

                incorrectAnswer = random.nextInt(41); // 41 because it is a total of both a&b(20 for each) -1

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

    public void playMultiplicationAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        timerView.setText("30s");
        pointsView.setText("0/0");
        resultTextView.setText("");
        newAddGameButton.setVisibility(View.INVISIBLE);
        newMultiplicationGameButton.setVisibility(View.INVISIBLE);

        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);

        timerView.setVisibility(View.VISIBLE);
        sumView.setVisibility(View.VISIBLE);
        pointsView.setVisibility(View.VISIBLE);

        generateMultiplicationQuestion();

        startMethod();
    }

    public void generateMultiplicationQuestion(){

        random = new Random();
        int a = random.nextInt(11);
        int b = random.nextInt(11);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear(); // has to be cleared every run of generateQuestion(); otherwise button view won't update

        int incorrectAnswer;

        sumView.setText(Integer.toString(a) + " * " + Integer.toString(b));

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer) {
                answers.add(a * b); //adding correct answer in random button
            } else {

                incorrectAnswer = random.nextInt(100);

                while (incorrectAnswer == a * b) { // while created because can be equal to the user answer
                    incorrectAnswer = random.nextInt(100);
                }
                answers.add(incorrectAnswer); // sum of total // potential issue: this result can be equal to the answer
            }
        }

        button4.setText(Integer.toString(answers.get(0)));
        button5.setText(Integer.toString(answers.get(1)));
        button6.setText(Integer.toString(answers.get(2)));
        button7.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswerAdd(View view){


                if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                    score++;
                    resultTextView.setText("Correct");
                } else {
                    resultTextView.setText("Wrong");
                }

                numberOfQuestions++;
                pointsView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            generateAddQuestion();
    }

    public void chooseAnswerMultiply(View view){


        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong");
        }

        numberOfQuestions++;
        pointsView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateMultiplicationQuestion();
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

        countDownTimer = new CountDownTimer(30 * 1000 + 100/*30100*/, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                //tableLayout.setVisibility(View.VISIBLE);
                newAddGameButton.setVisibility(View.VISIBLE);
                newMultiplicationGameButton.setVisibility(View.VISIBLE);
                timerView.setText("0:00");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

                timerView.setVisibility(View.INVISIBLE);
                sumView.setVisibility(View.INVISIBLE);
                pointsView.setVisibility(View.INVISIBLE);

                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                button5.setVisibility(View.INVISIBLE);
                button6.setVisibility(View.INVISIBLE);
                button7.setVisibility(View.INVISIBLE);
            }
        }.start();


    }

    public void startAdd(View view){

        startButtonAdd.setVisibility(View.INVISIBLE);
        startButtonMultiplication.setVisibility(View.INVISIBLE);
        GameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAddAgain(findViewById(R.id.newAddGame));

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        button7.setVisibility(View.INVISIBLE);
    }

    public void startMultiplication(View view){

        startButtonAdd.setVisibility(View.INVISIBLE);
        startButtonMultiplication.setVisibility(View.INVISIBLE);
        GameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playMultiplicationAgain(findViewById(R.id.newMultiplicationGame));

        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.VISIBLE);
        button5.setVisibility(View.VISIBLE);
        button6.setVisibility(View.VISIBLE);
        button7.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView  = (TextView) findViewById(R.id.timerView);
        sumView  = (TextView) findViewById(R.id.sumView);
        pointsView  = (TextView) findViewById(R.id.pointsView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        startButtonAdd = (Button) findViewById(R.id.startButtonAdd);
        startButtonMultiplication = (Button) findViewById(R.id.startButtonMultiplication);
        GameRelativeLayout = (RelativeLayout) findViewById(R.id.GamerelativeLayout);

        newAddGameButton = (Button) findViewById(R.id.newAddGame);
        newMultiplicationGameButton = (Button) findViewById(R.id.newMultiplicationGame);
        button0 = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);

    }
}
