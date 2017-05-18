package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.udojava.evalex.Expression;

public class MainActivity extends AppCompatActivity {

    private EditText calculations;
    private TextView result;


    // Variables to hold the operands and type of calculations
    private String number1 = null;
    private String number2 = null;
    private String operationSign = null;

    private String lastLetter;

    //użyte znaki
    // nie zmieniaj ich bo wtedy nie będzie działać kod niżej
    final String znakDzielenie = "÷";
    final String znakMnożenie = "×";
    final String znakOdejmowanie = "−";
    final String znakDodawanie = "+";
    final String kropka = ".";

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



                if (currentCalculations.length() > 0){
                    lastLetter = Character.toString(currentCalculations.charAt(currentCalculations.length() - 1));
                }

                switch (b.getText().toString()) {
                    case "DEL":
                        if (currentCalculations.length() != 0) {
                            calculations.setText(currentCalculations.substring(0,currentCalculations.length()-1));
                        }
                        break;

                    case kropka:
                        if (currentCalculations.length() != 0) {
                            if (!lastLetter.equals(kropka) && !Character.toString(currentCalculations.charAt(currentCalculations.length() - 2)).equals(kropka))
                            {
                                calculations.append(b.getText().toString());
                            }
                        }
                        break;

                    case znakDzielenie:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(kropka) || lastLetter.equals(znakMnożenie) || lastLetter.equals(znakDodawanie) || lastLetter.equals(znakOdejmowanie))
                            {
                                calculations.setText(currentCalculations.substring(0,currentCalculations.length()-1));
                            }
                            if (!lastLetter.equals(znakDzielenie))
                            {
                                calculations.append(b.getText().toString());
                            }
                        }
                        break;

                    case znakMnożenie:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(kropka) || lastLetter.equals(znakDzielenie) || lastLetter.equals(znakDodawanie) || lastLetter.equals(znakOdejmowanie))
                            {
                                calculations.setText(currentCalculations.substring(0,currentCalculations.length()-1));
                            }
                            if (!lastLetter.equals(znakMnożenie))
                            {
                                calculations.append(b.getText().toString());
                            }
                        }
                        break;

                    case znakDodawanie:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(kropka) || lastLetter.equals(znakMnożenie) || lastLetter.equals(znakDzielenie) || lastLetter.equals(znakOdejmowanie))
                            {
                                calculations.setText(currentCalculations.substring(0,currentCalculations.length()-1));
                            }
                            if (!lastLetter.equals(znakDodawanie))
                            {
                                calculations.append(b.getText().toString());
                            }
                        }
                        break;


                    case znakOdejmowanie:
                        if (currentCalculations.length() != 0) {
                            if (lastLetter.equals(kropka) || lastLetter.equals(znakMnożenie) || lastLetter.equals(znakDodawanie) || lastLetter.equals(znakDzielenie))
                            {
                                calculations.setText(currentCalculations.substring(0,currentCalculations.length()-1));
                            }
                            if (!lastLetter.equals(znakOdejmowanie))
                            {
                                calculations.append(b.getText().toString());
                            }
                        }
                        break;

                    default:
                        calculations.append(b.getText().toString());
                }

                //źródło: https://github.com/uklimaschewski/EvalEx
                String finalResult = calculations.getText().toString().replace('×','*').replace('÷','/').replace('−','-').replace('+','+');
                Expression expression = new Expression(finalResult);
                if (!lastLetter.equals(kropka) && !lastLetter.equals(znakDzielenie) && lastLetter.equals(znakMnożenie) && !lastLetter.equals(znakDodawanie) && !lastLetter.equals(znakOdejmowanie)) {
                    result.setText(expression.eval().toString()); //czemu działa tylko mnożenie?
                }

//wg mnie kod powinien być taki, ale jak jest taki to crashuje. Nie wiem czemu
//                String finalResult = calculations.getText().toString().replace('×','*').replace('÷','/').replace('−','-').replace('+','+');
//                Expression expression = new Expression(finalResult);
//                if (!lastLetter.equals(kropka) && !lastLetter.equals(znakDzielenie) //&& lastLetter.equals(znakMnożenie)
//                        && !lastLetter.equals(znakDodawanie) && !lastLetter.equals(znakOdejmowanie) && !lastLetter.equals("DEL")){
//                    result.setText(expression.eval().toString());
//                }



            } //onclick ends
        };


                for (int b = 0; b < 17; b++) {
                    button[b].setOnClickListener(listener);
                }


        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                calculations.setText("");
                return true;
            }
        };

        button[12].setOnLongClickListener(onLongClickListener);






    }

}
