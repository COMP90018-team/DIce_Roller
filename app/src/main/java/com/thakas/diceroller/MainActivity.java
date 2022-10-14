package com.thakas.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button rollButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollButton = findViewById(R.id.button);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll();
            }
        });
    }

    private  void display(int rollNumber, ImageView diceImage, TextView mResultText ){
        switch (rollNumber){
            case 1:
                diceImage.setImageResource(R.drawable.dice_1);
                mResultText.setText("One");
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice_2);
                mResultText.setText("Two");
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice_3);
                mResultText.setText("Three");
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice_4);
                mResultText.setText("Four");
                Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice_5);
                mResultText.setText("Five");
                Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice_6);
                mResultText.setText("Six");
                Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
                break;
            default:
                diceImage.setImageResource(R.drawable.empty_dice);
        }
    }

    private void roll() {
        int numberOfDice = 6;
        DiceCup diceCup = new DiceCup();
        diceCup.setDice(numberOfDice);

        ArrayList<Integer> topList = diceCup.getOnTops();


        // i is the index of the dice in dice cup
        for(int i = 0; i<numberOfDice; i++){
            int top = diceCup.getOnTops().get(i);
            ImageView diceImage;
            TextView mResultText;

            switch (i){
                case 0:
                    diceImage = findViewById(R.id.imageView0);
                    mResultText = findViewById(R.id.result_text0);
                    display(top, diceImage, mResultText);
                    break;
                case 1:
                    diceImage = findViewById(R.id.imageView1);
                    mResultText = findViewById(R.id.result_text1);
                    display(top, diceImage, mResultText);
                    break;
                case 2:
                    diceImage = findViewById(R.id.imageView2);
                    mResultText = findViewById(R.id.result_text2);
                    display(top, diceImage, mResultText);
                    break;
                case 3:
                    diceImage = findViewById(R.id.imageView3);
                    mResultText = findViewById(R.id.result_text3);
                    display(top, diceImage, mResultText);
                    break;
                case 4:
                    diceImage = findViewById(R.id.imageView4);
                    mResultText = findViewById(R.id.result_text4);
                    display(top, diceImage, mResultText);
                    break;
                case 5:
                    diceImage = findViewById(R.id.imageView5);
                    mResultText = findViewById(R.id.result_text5);
                    display(top, diceImage, mResultText);
                    break;
            }
        }

    }
}
