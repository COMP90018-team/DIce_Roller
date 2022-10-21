package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class DiceGameActivity extends AppCompatActivity {
    String selectParameter;
    Map<String, Integer> currentResultMap;
    Boolean is_cover = true;

    Integer[] matchImgList = {R.drawable.empty_dice,R.drawable.dice_1,R.drawable.dice_2,
            R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6};

    Integer[] TextBlkList = {R.id.result_text0,R.id.result_text1,R.id.result_text2,
            R.id.result_text3,R.id.result_text4,R.id.result_text5};

    String [] TextStrList = {"One", "Two", "Three", "Four", "Five", "Six"};

    Integer[] imgBlkList = {R.id.imageView0,R.id.imageView1,R.id.imageView2,R.id.imageView3,
            R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,
            R.id.imageView9,R.id.imageView10,R.id.imageView11};

    ArrayList<Integer> imgBlkStartList = new ArrayList<>();
    ArrayList<Integer> imgBlkTopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        for (int imgBlk: imgBlkList){
            MarginLayoutParams imgBlkPara = (MarginLayoutParams) findViewById(imgBlk).getLayoutParams();
            int imgBlkStart = imgBlkPara.leftMargin;
            imgBlkStartList.add(imgBlkStart);
            int imgBlkTop = imgBlkPara.topMargin;
            imgBlkTopList.add(imgBlkTop);
        }

        TextView showNumDices = findViewById(R.id.text_num_dice);
        selectParameter = getIntent().getStringExtra("selectParameter");
        showNumDices.setText("Playing with " + selectParameter + " Dices");
        currentResultMap = rollDice();

        ImageView diceCupImg = findViewById(R.id.imageDiceCup);
        Button btnOpen = findViewById(R.id.open_btn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_cover) {
                    displayStat(currentResultMap);
                    diceCupImg.setImageAlpha(0);
                    btnOpen.setText(R.string.cover_button);
                    is_cover = false;
                }

                else {
                    hideTextView();
                    diceCupImg.setImageAlpha(255);
                    btnOpen.setText(R.string.open_button);
                    is_cover = true;
                }
            }
        });

        Button btnShake = findViewById(R.id.shake_btn);
        btnShake.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // showContent.setText(R.string.test_text_3);
                is_cover = true;
                hideTextView();
                diceCupImg.setImageAlpha(255);
                btnOpen.setText(R.string.open_button);
                currentResultMap = rollDice();
            }
        });

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v ->
            finish()
        );
    }

    private void hideTextView(){
        for (int textBlk : TextBlkList) {
            TextView textBlkV = findViewById(textBlk);
            textBlkV.setVisibility(View.INVISIBLE);
        }
    }

    private  void display(int rollNumber, ImageView diceImage, int imgIndex){
        LayoutParams standard_params = findViewById(R.id.imageBase0).getLayoutParams();
        MarginLayoutParams params = (MarginLayoutParams)diceImage.getLayoutParams();
        if (rollNumber > 0){
            int max_bound = 5;
            int min_bound = -5;
            Random rand = new Random();
            int size_change = rand.nextInt((max_bound - min_bound) + 1) + min_bound;
            int horizontal_change = rand.nextInt((max_bound - min_bound) + 1) + min_bound;
            int vertical_change = rand.nextInt((max_bound - min_bound) + 1) + min_bound;
            params.width = standard_params.width + (size_change*6);
            params.height = standard_params.height + (size_change*4);
            params.leftMargin = imgBlkStartList.get(imgIndex) + (horizontal_change);
            params.topMargin = imgBlkTopList.get(imgIndex) + (vertical_change);
            diceImage.requestLayout();
        }
        diceImage.setImageResource(matchImgList[rollNumber]);
    }

    private void displaySubStat(Integer id, String s, Map<String, Integer> map){
        TextView textVSub = findViewById(id);
        textVSub.setVisibility(View.VISIBLE);
        textVSub.setText(s + ": " + map.get(s));
        if (map.get(s) == 0){
            textVSub.setTextColor(this.getResources().getColor(R.color.cream_100));
            textVSub.setTypeface(Typeface.create(textVSub.getTypeface(), Typeface.NORMAL));
        }
        if (map.get(s) != 0){
            textVSub.setTextColor(this.getResources().getColor(R.color.cream_200));
            textVSub.setTypeface(Typeface.create(textVSub.getTypeface(), Typeface.BOLD));
        }
    }

    private void displayStat(Map<String, Integer> map){
        for (int i = 0; i < TextStrList.length; i++){
            displaySubStat(TextBlkList[i], TextStrList[i], map);
        }
    }

    private Map<String, Integer> rollDice() {
        int numberOfDice = Integer.valueOf(selectParameter);
        DiceCup diceCup = new DiceCup();
        diceCup.setDice(numberOfDice);

        ArrayList<Integer> topList = diceCup.getOnTops();
        Map<String, Integer> resultMap = diceCup.getResultMap();

        // i is the index of the dice in dice cup
        for(int i = 0; i<12; i++){
            int top = topList.get(i);
            ImageView diceImage;
            diceImage = findViewById(imgBlkList[i]);
            display(top, diceImage, i);
        }
        // displayStat(resultMap);
        return resultMap;
    }
}