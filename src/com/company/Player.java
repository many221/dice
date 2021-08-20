package com.company;

import java.util.ArrayList;

public class Player {

    private  String name;

    private  int playerDie; //needs to be a non-static field

    private ArrayList <Integer> hand = new ArrayList<Integer> ( playerDie ); // Think of hand as inventory as in what's in the player's
    // possession such as dice or scorecards or money

    //will decide whether player is in game
    private boolean inGame = true;

    //Will be developed later
    private byte turnPosition;
    private int score;

    public Player(String name, int dice){

        this.name = name;

        this.playerDie = dice;

    }

    //Getters of fields

    public String getName() {
        return name;
    }

    public int getPlayerDie() {
        return playerDie;
    }

    public ArrayList<Integer> getHand() { return hand; }

    public boolean isInGame() { return inGame; }

    public void removeDice(){
        //for now it will only remove 1 dice
        byte loss = 1;

        playerDie -= loss;

        getHand ().remove ( 0 );
    }

    public void rollDice(){
        //O----------------Clear Previous Roll
        if (!hand.isEmpty ()){
         hand.clear ();
        }
        for (int i = 0; i < playerDie; i++) {

            getHand ().add ( (int) Dice.roll () );

        }
    }



}
