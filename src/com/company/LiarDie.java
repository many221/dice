package com.company;

import java.util.ArrayList;

import static java.lang.System.*;

public class LiarDie extends Game {

    private static final String rules = """
               Rules Of Liars Dice:
               1. All Player's Must Roll Their Dice.
               2. PLayers Bet Or Lie On The Quantity Of A Specific Value.
               3. The Following Player Can Either Call The Lie Or Place A Bet/Lie Of Their Own.
               4. If The Bet Is Called And It's True The Player Who Called It Loses A Dice.
               5. If The Bet is A Lie Then The Player Who Loses A Dice.
               6. The Match Will Continue Until Only One PLayer Is Left With Dice.
               7. All Of Losers Must Walk the PLANK!!!!!
               """;

    public LiarDie(){

        super ("Lair's Die" , rules, 5);

    }


    @Override
    public void Start() {
        super.Start ();

        //raison dêtre=> Welcome Message & Sets Player Count
        out.printf ( "You've Have Chosen To Play %s ARRRRRGH!!!\nHow many will be playing today?\nPlaying: ", getName () );
        setPlayerCount ( UI.num () );//Setting total players
        out.println ( "Please Enter Player(s) Name" );

        //raison dêtre=> Creates Playerrs and adds them to the list
        for (int i = 0; i < getPlayerCount (); i++) {

            out.print ( "Player " + ( i + 1 ) + " Name: " );
            String n = UI.str ();
            Player player = new Player ( n, getIntialDie () );
            addPlayer ( player );
        }

        /////////////////////////////////////////////////////////////////////////////
        //X Spacing
        for (int j = 0; j < 10; j++) {
            out.println ( "\n<|::||::||::||::||::||::||::||::||::|>\n" );
        }
        /////////////////////////////////////////////////////////////////////////////

        //X=== === === === || === === === ===X
        //raison dêtre=> Local Class to use methods within function
        class Table {


            private ArrayList<int[]> allBets = new ArrayList<int[]> ();

            public void placeBets(int v, int q) {

                int[] bets = {v, q};
                allBets.add ( bets );
            }

            //raison dêtre => Adding sorted dice
            private ArrayList<Integer> ones = new ArrayList<Integer> ();
            private ArrayList<Integer> twos = new ArrayList<Integer> ();
            private ArrayList<Integer> threes = new ArrayList<Integer> ();
            private ArrayList<Integer> fours = new ArrayList<Integer> ();
            private ArrayList<Integer> fives = new ArrayList<Integer> ();
            private ArrayList<Integer> sixes = new ArrayList<Integer> ();

            public ArrayList<ArrayList<Integer>> getAllDie() {

                ArrayList<ArrayList<Integer>> allDie = new ArrayList<ArrayList<Integer>> ();
                allDie.add ( ones );
                allDie.add ( twos );
                allDie.add ( threes );
                allDie.add ( fours );
                allDie.add ( fives );
                allDie.add ( sixes );

                return allDie;
            }

            private int diceInGame;

            public void sortDice(ArrayList<Integer> hand) {

                diceInGame += hand.size ();

                for (int i = 0; i < hand.size (); i++) {

                    int val = hand.get ( i );

                    switch (val) {
                        case 1 -> {
                            ones.add ( val );
                        }
                        case 2 -> {
                            twos.add ( val );
                        }
                        case 3 -> {
                            threes.add ( val );
                        }
                        case 4 -> {
                            fours.add ( val );
                        }
                        case 5 -> {
                            fives.add ( val );
                        }
                        case 6 -> {
                            sixes.add ( val );
                        }
                    }

                }
            }

            public void roll() {
                for (int i = 0; i < getPlayerCount (); i++) {
                    Player player = getPlayerList ().get ( i );
                    player.rollDice ();
                    sortDice ( player.getHand () );
                }

            }


        }
        Table table = new Table ();
        //X=== === === === || === === === ===X
        //O------------------------------------------------------------------Rolling
        table.roll ();
        //O------------------------------------------------------------------Rolling
        //TODO--------------------------------------------------Rounds
        int roundCount = 1;
        int lastBetV = 0;
        int lasBetQ = 0;

        getPlayerList ().trimToSize ();

        setGameCheck ( true );

        if (getPlayerList ().size () == 1) {

            setGameCheck ( false );
        }
        //TODO--------------------------------------------------Rounds

        while (isGameCheck ()) {


            ArrayList<String> plank = new ArrayList<String> ();

            for (int i = 0; i < getPlayerList ().size (); i++) {
                if (getPlayerList ().get ( i ).getHand ().isEmpty ()) {
                    plank.add ( getPlayerList ().get ( i ).getName () );
                    getPlayerList ().remove ( i );
                }
            }

            //raison dêtre => Player turn & Round Phase 1 TESTING

            out.println ( "\n Round " + roundCount + " Starting Now!!!\n" );

            //X------------------------------------------------------------------Betting
            int betV = lastBetV;
            int betQ = lasBetQ;

            lastBetV = betV;
            lasBetQ = betQ;

            //X------------------------------------------------------------------Betting

            for (int i = 0; i < getPlayerCount (); i++) {
                //I------------------------------------------------------------------Lying
                boolean cantLie = betV <= 0 && betQ <= 0;
                //I------------------------------------------------------------------Lying

                Player player = getPlayerList ().get ( i );

                out.println ( player.getName () + "'s Turn!\n" );

                //O------------------------------------------------------------------Rolling
                out.println ( "You have rolled " + player.getHand () + "\n" );
                //O------------------------------------------------------------------Rolling
                //I------------------------------------------------------------------Lying
                if (!cantLie) {

                    out.print ( "Is the Last Player Lying?\nY|N: " );
                    String yn = UI.str ();

                    switch (yn) {

                        case "Y", "y", "Yes", "yes" -> {

                            int index = betV - 1;

                            ArrayList<Integer> betArr = table.getAllDie ().get ( index );

                            boolean betCheck = betArr.size () <= betQ;

                            if (betCheck) {

                                out.println ( "They're Lying!" );

                                if (i == ( getPlayerCount () - 1 )) {

                                    getPlayerList ().get ( 0 ).removeDice ();

                                } else getPlayerList ().get ( ( i - 1 ) ).removeDice ();

                            } else if (!betCheck) {
                                out.println ( "They're Truthing! You've Lost A Die" );
                                player.removeDice ();
                            }

                        }
                        case "N", "n", "No", "no" -> out.println ( "You Said No" );

                    }

                }
                //I------------------------------------------------------------------Lying

                //X------------------------------------------------------------------Betting
                boolean betCheck = true;


                while (betCheck) {

                    out.print ( "\nPlease Bet On The Value Of The Die\nBet: " );
                    int playerBetV = UI.num ();
                    out.print ( "Please Bet On The Quantity Of The Die\nBet: " );
                    int playerBetQ = UI.num ();

                    int r1die = getPlayerCount () * getIntialDie ();

                    boolean round1 = playerBetV >= betV && playerBetQ <= r1die || playerBetQ > betQ && playerBetQ <= r1die;
                    boolean roud2UP = playerBetV >= betV && playerBetQ <= table.diceInGame || playerBetQ > betQ && playerBetQ <= table.diceInGame;
                    if (round1 && roundCount == 1) {
                        betCheck = false;
                        betV = playerBetV;
                        betQ = playerBetQ;
                        table.placeBets ( playerBetV, playerBetQ );
                    } else if (roud2UP) {
                        betCheck = false;
                        betV = playerBetV;
                        betQ = playerBetQ;
                        table.placeBets ( playerBetV, playerBetQ );
                    } else if (roundCount == 1) {
                        out.println ( "\n(<|:::!!INVAlID ENTRIES!!:::|>) \nPlease Enter Value >= " + betV + " & A Quantity > " + betQ + "\nOr A Quantity <= " + r1die );
                    } else {
                        out.println ( "\n(<|:::!!INVAlID ENTRIES!!:::|>) \nPlease Enter Value >= " + betV + " & A Quantity > " + betQ + "\nOr A Quantity <= " + table.diceInGame );
                    }
                }
                //X------------------------------------------------------------------Betting
                /////////////////////////////////////////////////////////////////////////////
                //X Spacing
                for (int j = 0; j < 10; j++) {
                    out.println ( "\n<|::||::||::||::||::||::||::||::||::|>\n" );
                }
                /////////////////////////////////////////////////////////////////////////////
                out.println ( player.getName () + " Bet The Value " + betV + " Is Present " + betQ + " Times \n" );

            }

            //raison dêtre => Tells how many die are on the table
            int diceLeft = 0;

            String summary = "";

            for (int i = 0; i < getPlayerList ().size (); i++) {
                diceLeft += getPlayerList ().get ( i ).getHand ().size ();
                summary += getPlayerList ().get ( i ).getName () + " has in hand " + getPlayerList ().get ( i ).getHand () + ".\n";
            }

            out.println ( "There are " + diceLeft + " dice on the table\n" + summary + "\nPlayers Who Have Walked The Plank " + plank );

            //TOdo================== new round stuff


        for (int j = 0; j < 2; j++) {
            out.println ("\n<|::||::||::||::||::||::||::||::||::|>\n");
        }

        roundCount++;
        out.println ("\n Round " + roundCount + " Starting Now!!!\n");

        }
        out.println ( "Congratulation " + getPlayerList ().get ( 0 ).getName () + "You Have Won\n" );
        out.print ( "Would you Like to Play Again?\nY|N: " );
        String yn = UI.str ();

        switch (yn) {

            case "Y", "y", "Yes", "yes" ->Start ();
            case "N", "n", "No", "no" ->{
                out.println ("Thank You For Playing Come Back Soon");
            }
        }
    }
}
//FIXME 1) Refresh dice and dice size per roll --[]
//FIXME 2) Link link last player bet to first player lie action --[]
//FIXME 3) Find Why sometimes proper bet comes back as lie
//FIXME 4) Fix endgame situation

