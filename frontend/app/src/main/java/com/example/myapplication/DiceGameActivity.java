package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class DiceGameActivity extends AppCompatActivity {

    String selectParameter;
    Map<String, Integer> currentResultMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        TextView showNumDices = (TextView) findViewById(R.id.text_num_dice);
        selectParameter = getIntent().getStringExtra("selectParameter");
        showNumDices.setText("Playing with " + selectParameter + " Dices");
        currentResultMap = rollDice();

        //TextView showContent = (TextView) findViewById(R.id.dicecup_img);
        ImageView diceCupImg = findViewById(R.id.imageDiceCup);
        Button btnOpen = (Button) findViewById(R.id.open_btn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diceCupImg.setAlpha(0);
                displayStat(currentResultMap);
            }
        });

        Button btnShake = (Button) findViewById(R.id.shake_btn);
        btnShake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // showContent.setText(R.string.test_text_3);
                hideTextView();
                diceCupImg.setAlpha(255);
                currentResultMap = rollDice();
            }
        });

        Button btnCover = (Button) findViewById(R.id.cover_btn);
        btnCover.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // showContent.setText(R.string.test_text_3);
                hideTextView();
                diceCupImg.setAlpha(255);
            }
        });

        Button btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void hideTextView(){
        TextView t0 = findViewById(R.id.result_text0);
        t0.setVisibility(View.INVISIBLE);
        TextView t1 = findViewById(R.id.result_text1);
        t1.setVisibility(View.INVISIBLE);
        TextView t2 = findViewById(R.id.result_text2);
        t2.setVisibility(View.INVISIBLE);
        TextView t3 = findViewById(R.id.result_text3);
        t3.setVisibility(View.INVISIBLE);
        TextView t4 = findViewById(R.id.result_text4);
        t4.setVisibility(View.INVISIBLE);
        TextView t5 = findViewById(R.id.result_text5);
        t5.setVisibility(View.INVISIBLE);
    }

    private  void display(int rollNumber, ImageView diceImage){
        switch (rollNumber){
            case 1:
                diceImage.setImageResource(R.drawable.dice_1);
                //mResultText.setText("One");
                //Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice_2);
                //mResultText.setText("Two");
                //Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice_3);
                //mResultText.setText("Three");
                //Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice_4);
                //mResultText.setText("Four");
                //Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice_5);
                //mResultText.setText("Five");
                //Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice_6);
                //mResultText.setText("Six");
                //Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            default:
                diceImage.setImageResource(R.drawable.empty_dice);
        }
    }
    private void displaySubStat(Integer id, String s, Map<String, Integer> map){
        TextView textVSub = findViewById(id);
        textVSub.setVisibility(View.VISIBLE);
        textVSub.setText(s + ": " + map.get(s));
        if (map.get(s) == 0){
            textVSub.setTextColor(this.getResources().getColor(R.color.cream_100));
        }
        if (map.get(s) != 0){
            textVSub.setTextColor(this.getResources().getColor(R.color.cream_200));
        }
    }

    private void displayStat(Map<String, Integer> map){
        displaySubStat(R.id.result_text0, "One", map);
        displaySubStat(R.id.result_text1, "Two", map);
        displaySubStat(R.id.result_text2, "Three", map);
        displaySubStat(R.id.result_text3, "Four", map);
        displaySubStat(R.id.result_text4, "Five", map);
        displaySubStat(R.id.result_text5, "Six", map);
    }

    private Map<String, Integer> rollDice() {
        int numberOfDice = Integer.valueOf(selectParameter);
        DiceCup diceCup = new DiceCup();
        diceCup.setDice(numberOfDice);

        ArrayList<Integer> topList = diceCup.getOnTops();
        Map<String, Integer> resultMap = diceCup.getResultMap();

        // i is the index of the dice in dice cup
        for(int i = 0; i<numberOfDice; i++){
            int top = diceCup.getOnTops().get(i);
            ImageView diceImage;
            TextView mResultText;

            switch (i){
                case 0:
                    diceImage = findViewById(R.id.imageView0);
                    //mResultText = findViewById(R.id.result_text0);
                    display(top, diceImage);
                    break;
                case 1:
                    diceImage = findViewById(R.id.imageView1);
                    //mResultText = findViewById(R.id.result_text1);
                    display(top, diceImage);
                    break;
                case 2:
                    diceImage = findViewById(R.id.imageView2);
                    //mResultText = findViewById(R.id.result_text2);
                    display(top, diceImage);
                    break;
                case 3:
                    diceImage = findViewById(R.id.imageView3);
                    //mResultText = findViewById(R.id.result_text3);
                    display(top, diceImage);
                    break;
                case 4:
                    diceImage = findViewById(R.id.imageView4);
                    //mResultText = findViewById(R.id.result_text4);
                    display(top, diceImage);
                    break;
                case 5:
                    diceImage = findViewById(R.id.imageView5);
                    //mResultText = findViewById(R.id.result_text5);
                    display(top, diceImage);
                    break;
            }
        }
//        displayStat(resultMap);
        return resultMap;
    }
}