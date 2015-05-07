package com.example.jack.simplemath;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends Activity {



    private int randNum1;
    private int randNum2;
    private int numCorrect;
    private boolean reset = true;
    private int usrAnsAdd;
    private int usrAnsSubtract;
    private int usrAnsMultiply;



    public static int randInt(int min, int max) {

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    private void randUpdate() {
        randNum1 = randInt(0,10);
        randNum2 = randInt(0,10);
        additionUpdate();
        subtractionUpdate();
        multiplicationUpdate();
    }
    private void additionUpdate() {
        TextView additionView = (TextView) findViewById(R.id.additionView);
        additionView.setText(randNum1 + " + " + randNum2);
    }
    private void subtractionUpdate() {
        TextView subtractionView = (TextView) findViewById(R.id.subtractionView);
        subtractionView.setText(randNum1 + " + " + randNum2);
    }
    private void multiplicationUpdate() {
        TextView multiplicationView = (TextView) findViewById(R.id.multiplicationView);
        multiplicationView.setText(randNum1 + " + " + randNum2);
    }
    private void correctUpdate() {
        TextView correctView = (TextView) findViewById(R.id.correctView);
        ImageView oneStar = (ImageView)findViewById(R.id.oneStar);
        ImageView twoStar = (ImageView)findViewById(R.id.twoStar);
        ImageView threeStar = (ImageView)findViewById(R.id.threeStar);

        if (reset == false) {
            switch (numCorrect) {
                case 1: correctView.setText("You got 1 question correct!");
                    oneStar.setVisibility(View.VISIBLE);
                    break;
                case 2: correctView.setText("You got 2 question correct!");
                    oneStar.setVisibility(View.VISIBLE);
                    twoStar.setVisibility(View.VISIBLE);
                    break;
                case 3: correctView.setText("You got 3 question correct!");
                    oneStar.setVisibility(View.VISIBLE);
                    twoStar.setVisibility(View.VISIBLE);
                    threeStar.setVisibility(View.VISIBLE);
                    break;
                default: correctView.setText("how the hell");
            }
        }
        else if (reset == true) {
            correctView.setText("");
            oneStar.setVisibility(View.INVISIBLE);
            twoStar.setVisibility(View.INVISIBLE);
            threeStar.setVisibility(View.INVISIBLE);
            reset = false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randUpdate();
        Button submitAnswers = (Button) findViewById(R.id.submitButton);
        submitAnswers.setOnClickListener(submitAnswersListener);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(resetButtonListener);
        EditText usrAdd = (EditText) findViewById(R.id.usrAdd);
        usrAdd.addTextChangedListener(usrAddListener);
        EditText usrSubtract = (EditText) findViewById(R.id.usrSubtract);
        usrSubtract.addTextChangedListener(usrSubtractListener);
        EditText usrMultiply = (EditText) findViewById(R.id.usrMultiply);
        usrMultiply.addTextChangedListener(usrMultiplyListener);
    }

    private OnClickListener submitAnswersListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (randNum1 + randNum2 == usrAnsAdd) {
                numCorrect++;
            }
            if (randNum1 - randNum2 == usrAnsSubtract) {
                numCorrect++;
            }
            if (randNum1 * randNum2 == usrAnsMultiply) {
                numCorrect++;
            }
        }
    };
    private OnClickListener resetButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            reset = true;
            correctUpdate();
            randUpdate();

        }
    };
    private TextWatcher usrAddListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try
            {
                usrAnsAdd = Integer.parseInt(s.toString(), 10);
            }
            catch (NumberFormatException e)
            {
                usrAnsAdd = 0;
            }
        }
    };
    private TextWatcher usrSubtractListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try
            {
                usrAnsSubtract = Integer.parseInt(s.toString(), 10);
            }
            catch (NumberFormatException e)
            {
                usrAnsSubtract = 0;
            }
        }
    };
    private TextWatcher usrMultiplyListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try
            {
                usrAnsMultiply = Integer.parseInt(s.toString(), 10);
            }
            catch (NumberFormatException e)
            {
                usrAnsMultiply = 0;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
