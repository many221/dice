package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private  String name;

    private  byte die; //needs to be a non-static field

    private ArrayList <Byte> hand = new ArrayList<Byte> (die); // Think of hand as inventory as in what's in the player's
    // possession such as dice or scorecards or money

    //will decide whether player is in game
    private boolean inGame = true;

    //Will be developed later
    private byte turnPosition;
    private int score;

    public Player(String name, Byte dice){

        this.name = name;

        this.die = dice;

    }

    //Getters of fields

    public String getName() {
        return name;
    }

    public byte getDie() {
        return die;
    }

    public ArrayList<Byte> getHand() { return hand; }

    public boolean isInGame() { return inGame; }

    public void removeDice(){
        //for now it will only remove 1 dice
        byte loss = 1;

        die -= loss;

        getHand ().remove ( 0 );
    }

    public void rollDice(){

        for (int i = 0; i < die ; i++) {

            getHand ().add ( Dice.roll () );

        }
    }



}
