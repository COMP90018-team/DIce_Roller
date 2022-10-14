package com.thakas.diceroller;

// Package添加:
import java.util.ArrayList;

public class DiceCup {

    // Attributes
    private ArrayList<Dice> cup;
    private int numberOfDice;


    // constructor


    public DiceCup(){
        this.cup = new ArrayList<Dice>();
        this.numberOfDice = 0;
    }

    // fill the dice cup with specified numbers of dice

    public void setDice(int numberOfDice){
        this.numberOfDice = numberOfDice;
        for(int i =0; i< numberOfDice; i++){
            Dice d = new Dice();
            this.cup.add(d);
        }

    }

    // Add Dice
    public void addDice(Dice d){
        this.cup.add(d);
        this.numberOfDice++;
    }

    // Shake Dice Cup
    public void shakeDiceCup(){
        for (Dice d: cup) {
            d.randomDice();
        }
    }

    // Return the top results of all dice
    public ArrayList<Integer> getOnTops(){
        ArrayList<Integer> topList = new ArrayList<Integer>();
        for(int i=0; i<this.numberOfDice; i++){
            Dice temp = cup.get(i);
            topList.add(temp.getOnTop());
        }
        return topList;
    }

    public ArrayList<Integer> getResultList() {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for(int i=0; i<this.numberOfDice; i++){
            Dice temDice = cup.get(i);
            resultList.add(temDice.getOnTop());
        }
        return resultList;
    }
}
