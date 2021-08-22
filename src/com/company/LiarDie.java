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

    public LiarDie() {

        super ( "Lair's Die", rules, 5 );

    }


    @Override
    public void Start() {
        super.Start ();

        //raison dêtre=> Welcome Message & Sets Player Count
        out.printf ( "You've Have Chosen To Play %s ARRRRRGH!!!\nHow many will be playing today? Game will only start if more than 2 players join\nPlaying: ", getName () );
        setPlayerCount ( UI.num () );//Setting total players
        out.println ( "Please Enter Player(s) Name" );

//        if (getPlayerCount () < 2) {
//            Start ();
//        }

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

        //raison dêtre => Player removal
        ArrayList<String> plank = new ArrayList<String> ();

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

            public void clearRoll() {

                ones.clear ();
                twos.clear ();
                threes.clear ();
                fours.clear ();
                fives.clear ();
                sixes.clear ();
                diceInGame = 0;
            }


        }
        Table table = new Table ();
        //X=== === === === || === === === ===X
        //O------------------------------------------------------------------Rolling
        table.roll ();
        //O------------------------------------------------------------------Rolling
        //TODO--------------------------------------------------Rounds
        int roundCount = 1;

        int betV = 0;
        int betQ = 0;
        getPlayerList ().trimToSize ();

        out.println ( "\n(1) Round " + roundCount + " Starting Now!!!\n" );

        setGameCheck ( true );

        if (getPlayerList ().size () == 1) {

            setGameCheck ( false );
        }
        //TODO--------------------------------------------------Rounds
        //TODO ADD BACK THE LINK BETWEEN FIRST AND LAST
        while (isGameCheck ()) {


            for (int i = 0; i < getPlayerList ().size (); i++) {

                Player player = getPlayerList ().get ( i );
//                if (player.getHand ().isEmpty ()) {
//                    plank.add ( player.getName () );
//                    removePlayer ( player );
//                }

            //X------------------------------------------------------------------TBetting
//            int betV = 0;//0
//            int betQ = 0;//0

            //X------------------------------------------------------------------TBetting


                //I------------------------------------------------------------------Lying
                boolean cantLie = betV <= 0 && betQ <= 0;
                //I------------------------------------------------------------------Lying



                out.println ( player.getName () + "'s Turn!\n" );

                //O------------------------------------------------------------------Rolling
                out.println ( "You have rolled " + player.getHand () );
                //O------------------------------------------------------------------Rolling

                if (cantLie && roundCount <= 1) {
                    out.println ( "NO BETS CAN BE MADE" );
                }
                //I------------------------------------------------------------------Lying
                if (!cantLie || roundCount > 1) {
                    out.println ( "The last round value bet was " + betV + "\nThe last round Quantity bet was " + betQ + "\n\n" );
                    out.print ( "Call A Bluff?\nY|N: " );
                    String yn = UI.str ();

                    switch (yn) {

                        case "Y", "y", "Yes", "yes" -> {
                            //TODO: Figure out how to reset round here --[]

                            int index = betV - 1;
                            out.println ( "Checking Array: " + index );

                            ArrayList<Integer> betArr = table.getAllDie ().get ( index );

                            boolean betCheck = betArr.size () <= betQ;

                            if (betCheck) {

                                out.println ( "They're Lying!" );

                                //FIXME -------------------------------- --[x]
                                //If it's the last player
                                if (i == 0) {

                                    getPlayerList ().get ( getPlayerCount () - 1 ).removeDice ();

                                } else getPlayerList ().get ( ( i - 1 ) ).removeDice ();
                                //FIXME -------------------------------- --[x]


                            } else if (!betCheck) {
                                out.println ( "They're Truthing! You've Lost A Die" );
                                player.removeDice ();

                            }
                            betQ = 0;
                            betV = 0;
                            table.clearRoll ();
                            table.roll ();
                            out.println ( "All Die Have Been Rerolled\n" + "Your new hand is " + player.getHand () );
                        }
                        case "N", "n", "No", "no" -> {
//                            table.clearRoll ();
//                            table.roll ();
                            out.println ("\nYour hand is " + player.getHand () );

                        }

                    }

                }
                //I------------------------------------------------------------------Lying
                if (player.getHand ().isEmpty ()) {

                    out.println ( player.getName () + " Has Lost!!" );
                    break;
                }
                //X------------------------------------------------------------------Betting
                out.print ( table.diceInGame + " Die left\nPlease Bet On The Value Of The Die\nBet: " );
                int playerBetV = UI.num ();
                out.print ( "Please Bet On The Quantity Of The Die\nBet: " );
                int playerBetQ = UI.num ();

                int r1die = getPlayerCount () * getIntialDie ();

                boolean round1 = playerBetV >= betV && playerBetQ <= r1die || playerBetQ > betQ && playerBetQ <= r1die;
                //LaterDice
                boolean roud2UP = playerBetV >= betV && playerBetQ <= table.diceInGame || playerBetQ > betQ && playerBetQ <= table.diceInGame;
                if (round1 ) {

                    betV = playerBetV;
                    betQ = playerBetQ;
                    table.placeBets ( playerBetV, playerBetQ );

                } else if (roud2UP) {

                    betV = playerBetV;
                    betQ = playerBetQ;
                    table.placeBets ( playerBetV, playerBetQ );

                } else if (roundCount == 1) {

                    out.println ( "\n(<|:::!!INVAlID ENTRIES!!:::|>) \nPlease Enter Value >= " + betV + " & A Quantity > " + betQ + "\nOr A Quantity <= " + r1die );
                } else {
                    out.println ( "\n(<|:::!!INVAlID ENTRIES!!:::|>) \nPlease Enter Value >= " + betV + " & A Quantity > " + betQ + "\nOr A Quantity <= " + table.diceInGame );
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
                out.println ( "\n<|::||::||::||::||::||::||::||::||::|>\n" );
            }

            if (getPlayerCount () < 2) {
                out.println ( "Congratulation " + getPlayerList ().get ( 0 ).getName () + "You Have Won\n" );
                out.print ( "Would you Like to Play Again?\nY|N: " );
                String yn = UI.str ();

                switch (yn) {

                    case "Y", "y", "Yes", "yes" -> Start ();
                    case "N", "n", "No", "no" -> {
                        out.println ( "Thank You For Playing Come Back Soon" );
                    }
                }
            }

            roundCount++;
            out.println ( "\n (2) Round " + roundCount + " Starting Now!!!\n" );

        }


    }

}



//FIXME  Fix endgame situation
//FIXME Fix


