package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText calculations;
    private TextView result;


    // Variables to hold the operands and type of calculations
    private String number1 = null;
    private String number2 = null;
    private String operationSign = null;

    private String lastLetter;
    private String lastSymbol;

    final String divisionSymbol = "÷";
    final String multiplicationSymbol = "×";
    final String subtractionSymbol = "−";
    final String addSymbol = "+";
    final String dot = ".";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calculations = (EditText) findViewById(R.id.calculations);
        result = (TextView) findViewById(R.id.result);

        int[] buttonIds = {
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
                R.id.button_dot,
                R.id.button_equal,
                R.id.button_del,
                R.id.button_divide,
                R.id.button_times,
                R.id.button_substract,
                R.id.button_add,
        };
        Button[] button = new Button[17];
        for (int b = 0; b < 17; b++) {
            button[b] = (Button) findViewById(buttonIds[b]);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;

                String currentCalculations = calculations.getText().toString();


                if (currentCalculations.length() > 0) {
                    lastLetter = Character.toString(currentCalculations.charAt(currentCalculations.length() - 1));
                }

                switch (b.getText().toString()) {
                    case "DEL":
                        if (currentCalculations.length() != 0) {
                            calculations.setText(currentCalculations.substring(0, currentCalculations.length() - 1));
                            lastSymbol = "";
                        }
                        break;

                    case dot:
                        if (currentCalculations.length() != 0) {
                            if (!lastLetter.equals(dot) && !Character.toString(currentCalculations.charAt(currentCalculations.length() - 2)).equals(dot)) {
                                calculations.append(b.getText().toString());
                                lastSymbol = ".";
                            }

                        }
                        break;

                    case divisionSymbol:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(dot) || lastLetter.equals(multiplicationSymbol) || lastLetter.equals(addSymbol) || lastLetter.equals(subtractionSymbol)) {
                                calculations.setText(currentCalculations.substring(0, currentCalculations.length() - 1));
                            }
                            if (!lastLetter.equals(divisionSymbol)) {
                                calculations.append(b.getText().toString());
                                lastSymbol = "/";
                            }
                        }
                        break;

                    case multiplicationSymbol:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(dot) || lastLetter.equals(divisionSymbol) || lastLetter.equals(addSymbol) || lastLetter.equals(subtractionSymbol)) {
                                calculations.setText(currentCalculations.substring(0, currentCalculations.length() - 1));
                            }
                            if (!lastLetter.equals(multiplicationSymbol)) {
                                calculations.append(b.getText().toString());
                                lastSymbol = "*";
                            }
                        }
                        break;

                    case addSymbol:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(dot) || lastLetter.equals(multiplicationSymbol) || lastLetter.equals(divisionSymbol) || lastLetter.equals(subtractionSymbol)) {
                                calculations.setText(currentCalculations.substring(0, currentCalculations.length() - 1));
                            }
                            if (!lastLetter.equals(addSymbol)) {
                                calculations.append(b.getText().toString());
                                lastSymbol = "+";
                            }
                        }
                        break;


                    case subtractionSymbol:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(dot) || lastLetter.equals(multiplicationSymbol) || lastLetter.equals(addSymbol) || lastLetter.equals(divisionSymbol)) {
                                calculations.setText(currentCalculations.substring(0, currentCalculations.length() - 1));
                            }
                            if (!lastLetter.equals(subtractionSymbol)) {
                                calculations.append(b.getText().toString());
                                lastSymbol = "-";
                            }
                        }
                        break;

                    default:
                        calculations.append(b.getText().toString());
                }

                Log.e("tag", String.valueOf(Integer.parseInt(calculations.getText().toString().replace(divisionSymbol, "/").replace(multiplicationSymbol, "*").replace(addSymbol, "+").replace(subtractionSymbol, "-"))));

            } //onclick ends
        };


        for (int b = 0; b < 17; b++) {
            button[b].setOnClickListener(listener);
        }

        button[12].setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                calculations.setText("");
                return true;
            }
        });


    }

}
