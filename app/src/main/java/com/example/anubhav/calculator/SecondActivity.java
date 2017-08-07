package com.example.anubhav.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }
}
