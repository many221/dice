package com.company;

import java.util.Arrays;

public class Player {

    private final String name;
    private static byte die;

    private static byte[] hand; // Think of hand as inventory as in what's in the player's
    // possession such as dice or scorecards or money

    //Will be used in table class
    private byte turnPosition;
    private int score;

    public Player(String name){

        this.name = name;

        hand = new byte[die];

    }

    //this may move to table class
    public static void setDie(byte die) {
        Player.die = die;
    }

    public String getName() {
        return name;
    }

    public static byte getDie() {
        return die;
    }

     public  static byte[] getHand() {
        return hand;
    }

    //this may also move to table class
    public static void rollDice(){

        for (int i = 0; i < die ; i++) {

            getHand()[i] = Dice.roll ();
        }
    }



    //Testing remove a die from player class but will move to table class

    //Testing if player can roll dice with each roll random
    public static void main(String[] args) {
        Player.setDie ( (byte) 5);

        Player player1 = new Player ( "Bob");
        Player player2 = new Player ( "Jim" );

        //FIXME with this order the last value over rides the previous players roll
//        player1.rollDice();
//        player2.rollDice();

        //This order does not
        player1.rollDice ();
        System.out.println (player1.name + " --- " + player1.getDie () + " --- " + Arrays.toString ( player1.getHand () ) );
        player1.rollDice ();
        System.out.println (player2.name + " --- " + player2.getDie () + " --- " + Arrays.toString ( player2.getHand () ) );
        
        //As seen here so figure out how to keep it and not reset it
        System.out.println (player1.name + " --- " + player1.getDie () + " --- " + Arrays.toString ( player1.getHand () ) );
    }

}
