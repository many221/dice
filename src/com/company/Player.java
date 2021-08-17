package com.company;

import java.util.Arrays;

public class Player {

    private  String name;
    private  byte die; //needs to be a non-static field
    private  byte[] hand; // Think of hand as inventory as in what's in the player's
    // possession such as dice or scorecards or money

    //Will be used in table class
    private byte turnPosition;
    private int score;

    public Player(String name, Byte dice){

        this.name = name;
        this.die = dice;
        hand = new byte[die];
    }


    //Getters of fields

    public String getName() {
        return name;
    }

    public byte getDie() {
        return die;
    }

    public byte[] getHand() {
        return hand;
    }

    //this may also move to table class
    public void rollDice(){

        for (int i = 0; i < getDie() ; i++) {

            getHand()[i] = Dice.roll ();
        }
    }

    public void removeDice(){
        //for now it will only remove 1 dice
        byte loss = 1;
        die -= loss;
    }

}
