package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {



        Player player1 = new Player ( "Bob",(byte) 5);
        Player player2 = new Player ( "Jim",(byte) 5 );

        //FIXME with this order the last value over rides the previous players roll --[x]
//        player1.rollDice();
//        player2.rollDice();
//
//        //This order does not
//        player1.rollDice ();
//        player2.rollDice ();
//        System.out.println (player1.getName () + " --- " + player1.getDie () + " --- " + Arrays.toString ( player1.getHand () ) );
//        System.out.println (player2.getName () + " --- " + player2.getDie () + " --- " + Arrays.toString ( player2.getHand () ) );
//        player1.rollDice ();
//        //As seen here so figure out how to keep it and not reset it
//        System.out.println (player1.getName () + " --- " + Arrays.toString ( player1.getHand () ) );

        player1.rollDice ();//sets the dice in players hand
        player2.rollDice ();
        System.out.println (player1.getName () + " --- " + player1.getDie () + " --- " + Arrays.toString ( player1.getHand () ) );
        player1.rollDice ();
        System.out.println (player2.getName () + " --- " + player2.getDie () + " --- " + Arrays.toString ( player2.getHand () ) );
        System.out.println (player1.getName () + " --- " + player1.getDie () + " --- " + Arrays.toString ( player1.getHand () ) );

        for (int i = 0; i < 3; i++) {
            player2.rollDice ();
            System.out.println ( "--------------------\n"+player2.getName () + " --- " + player2.getDie () + " --- " + Arrays.toString ( player2.getHand () ) + "\n--------------------" );
        }
    }
}
