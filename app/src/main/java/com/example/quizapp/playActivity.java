package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {"Which of the following is NOT a valid access modifier in Java ?",
            "Which keyword is used to create an object in Java ?","Which data type is used to create a variable that should store text ?"
            ,"What is the default value of a boolean variable in Java ?",
           "What is the keyword used to inherit a class in Java ?"
    };
    String[] choose_list = {"public","private","protected","static",
            "new","create","object","instance",
            "string","txt","text","char",
            "true","false","0","null",
            "implements","extends","inherits","super"
    };
    String[] correct_list = {"static","new","String","false","extends"};


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                        if (isclickBtn){
                            isclickBtn = false;

                            if(!valueChoose.equals(correct_list[currentQuestion])){
                                Toast.makeText(playActivity.this , "Incorrect",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                            }else {
                                Toast.makeText(playActivity.this , "correct",Toast.LENGTH_LONG).show();
                                btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                                scorePlayer++;
                            }
                            new Handler().postDelayed(() -> {
                                if(currentQuestion!=question_list.length-1){
                                    currentQuestion = currentQuestion + 1;
                                    remplirData();
                                    valueChoose = "";
                                    btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                    btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                                }else {
                                    Intent intent  = new Intent(playActivity.this , ResulteActivity.class);
                                    intent.putExtra("Resute" , scorePlayer);
                                    startActivity(intent);
                                    finish();
                                }

                            },2000);

                        }else {
                            Toast.makeText(playActivity.this ,  "You May Choose One",Toast.LENGTH_LONG).show();
                        }
                }
        );


    }

    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}