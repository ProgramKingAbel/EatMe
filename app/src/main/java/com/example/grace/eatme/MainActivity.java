package com.example.grace.eatme;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtSlogan;
    Button logIn ,signUp;
    RelativeLayout mylayout;
    AnimationDrawable myanimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn=(Button)findViewById(R.id.btn_LogIn);
        signUp=(Button)findViewById(R.id.btn_signUp);
        txtSlogan=(TextView)findViewById(R.id.txtSlogan);
        mylayout=(RelativeLayout) findViewById(R.id.mybigdraw);
        myanimation=(AnimationDrawable)mylayout.getBackground();

        myanimation.setEnterFadeDuration(4500);
        myanimation.setExitFadeDuration(4500);

        myanimation.start();



        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this,LogIn.class);
                startActivity(login);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);

            }
        });

    }



}
