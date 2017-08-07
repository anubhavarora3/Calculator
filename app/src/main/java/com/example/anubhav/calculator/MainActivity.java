package com.example.anubhav.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.edtInput)
    EditText edtInput;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.btnSign)
    Button btnSign;
    @BindView(R.id.btnModulo)
    Button btnModulo;
    @BindView(R.id.btnDiv)
    Button btnDiv;
    @BindView(R.id.btnSeven)
    Button btnSeven;
    @BindView(R.id.btnEight)
    Button btnEight;
    @BindView(R.id.btnNine)
    Button btnNine;
    @BindView(R.id.btnMul)
    Button btnMul;
    @BindView(R.id.btnFour)
    Button btnFour;
    @BindView(R.id.btnFive)
    Button btnFive;
    @BindView(R.id.btnSix)
    Button btnSix;
    @BindView(R.id.btnSub)
    Button btnSub;
    @BindView(R.id.btnOne)
    Button btnOne;
    @BindView(R.id.btnTwo)
    Button btnTwo;
    @BindView(R.id.btnThree)
    Button btnThree;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnZero)
    Button btnZero;
    @BindView(R.id.btnDecimal)
    Button btnDecimal;
    @BindView(R.id.btnEqu)
    Button btnEqu;

    private String input = "", input1 = "", input2 = "", operation = "", operator = "", expression = "";
    private Double result = 0.0;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int display_mode = getResources().getConfiguration().orientation;
//        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
//            setContentView(R.layout.activity_main);
//        } else {
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnNine.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnModulo.setOnClickListener(this);
        btnDecimal.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnEqu.setOnClickListener(this);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNine || v.getId() == R.id.btnEight || v.getId() == R.id.btnSeven
                || v.getId() == R.id.btnSix || v.getId() == R.id.btnFive || v.getId() == R.id.btnFour
                || v.getId() == R.id.btnThree || v.getId() == R.id.btnTwo || v.getId() == R.id.btnOne
                || v.getId() == R.id.btnZero || v.getId() == R.id.btnDecimal || v.getId() == R.id.btnSign) {

            if (v.getTag().toString().equalsIgnoreCase("Negative") && input == "") {
                input += "-";
            } else {
                input += v.getTag();
            }
            edtInput.setText(input);

        } else if (v.getId() == R.id.btnAdd || v.getId() == R.id.btnSub
                || v.getId() == R.id.btnMul || v.getId() == R.id.btnDiv ||
                v.getId() == R.id.btnModulo) {

            if (flag == 1) {
                expression = result.toString();
                flag = 0;
            } else {
                expression += input;
            }
            operation += v.getTag();

            if (operation.equalsIgnoreCase("Addition")) {
                operator = "+";
            } else if (operation.equalsIgnoreCase("Subtraction")) {
                operator = "-";
            } else if (operation.equalsIgnoreCase("Multiplication")) {
                operator = "*";
            } else if (operation.equalsIgnoreCase("Divison")) {
                operator = "/";
            } else if (operation.equalsIgnoreCase("Modulo")) {
                operator = "%";
            }
            expression += operator;
            input = "";
            operation = "";

        } else if (v.getId() == R.id.btnEqu) {
            expression += input;
            result = eval(expression);
            //input = result.toString();
            flag = 1;
            edtInput.setText(String.valueOf(result));

        } else if (v.getId() == R.id.btnClear) {
            edtInput.setText("0");
            expression = "";
            flag = 0;
            operation = input = "";
        }
    }


    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else if (eat('%')) x %= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
