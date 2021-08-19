package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.*;

public class LiarDie extends Game {

    private static final String rules = """
               Rules Of The Game:
               1. YArr
               2. Argghhhh
               3. Parrot Squak
               4. Pirate Hook
               5. YArrrgh
               6. Parrot Squak
               7. YArrrgh
               8. YARRR
               9. YArrrgh
               10.Parrot Squak
               """;

    public LiarDie(){

        super ("Lair's Die" , rules, 5);

    }


    @Override
    public void Start() {
        super.Start ();

        //raison dêtre=> Welcome Message & Sets Player Count
        out.printf ( "You've Have Chosen To Play %s ARRRRRGH!!!\nHow many will be playing today?\nPlaying: ", getName () );
        setPlayerCount ( UI.num ());//Setting total players
        out.println ("Please Enter Player(s) Name");

        //raison dêtre=> Creates Playerrs and adds them to the list
        for (int i = 0; i < getPlayerCount (); i++) {

            out.print ("Player " + (i+1) + " Name: ");
            String n = UI.str ();
            Player player = new Player (n, getIntialDie ());
            addPlayer ( player );
        }

        /////////////////////////////////////////////////////////////////////////////
        //X Spacing
        for (int j = 0; j < 10; j++) {
            out.println ("\n<|::||::||::||::||::||::||::||::||::|>\n");
        }
        /////////////////////////////////////////////////////////////////////////////



        //X=== === === === || === === === ===X
        //raison dêtre=> Testing Local Class to use methods within function
        class Table{


            private ArrayList<int[]>allBets = new ArrayList<int[]> ();

            public void placeBets(int v, int q){

                int[] bets = {v,q};
                allBets.add ( bets );
            }

            //raison dêtre => Adding sorted dice
            private ArrayList<Integer> ones = new ArrayList<Integer> ();
            private ArrayList<Integer> twos = new ArrayList<Integer> ();
            private ArrayList<Integer> threes = new ArrayList<Integer> ();
            private ArrayList<Integer> fours = new ArrayList<Integer> ();
            private ArrayList<Integer> fives = new ArrayList<Integer> ();
            private ArrayList<Integer> sixes= new ArrayList<Integer> ();

            private int diceInGame;

            public void sortDice(ArrayList<Integer> hand){

                diceInGame += hand.size ();

                for (int i = 0; i < hand.size (); i++) {

                    int val = hand.get ( i );

                    switch (val){
                        case 1 ->{ones.add ( val );}
                        case 2 ->{twos.add ( val );}
                        case 3 ->{threes.add ( val );}
                        case 4 ->{fours.add ( val );}
                        case 5 ->{fives.add ( val );}
                        case 6 ->{sixes.add ( val );}
                    }

                }
            }


        }

        Table table = new Table ();




        //X=== === === === || === === === ===X

        //TODO: Turn/Round system ===[]
        //TODO: Just figure out the type and the order they go!!!! IT'S CAKE WITH LAYERS
        //A full a round is all turns taken
        //Break down the loops
        //Each player gets a turn
        //Each player can participate in the turn of who's IT
        //It/turn will rotate between all players so its for each player loop
        //Rounds will end when the last player turns end
        //Rounds will keep going until there is only one player left or the limit is hit

        //TODO: Data mod/creation loops ===[]
        //the bigO wont be happy with this but MVP and reduce possible loops after it works
        //becuase I dont know the amount of players it all has to coded with loops
        //Data needs to be distributed through several layers of the loops
        //Data is also produced from the loops
        //There are two types of loops those the modify based off of conditional statements and this that produce data


        //Now I need to set the round system
        //Keep playing for until 1 Player left or rounds
        //Ask how to add extra fields to object within another class
        //TODO: Make turn System ===[]
        //TODO: Make diceGame size change when dice removed ===[]
        //TODO: Be able to compare bets and find winner based off of conditionals ===[]
        //TODO: Make bet conditional ===[]
        //TODO: Make Winner Conditonal ===[]

        //TODO store any variables to be monitored from rounds here

        int roundCount = 1;

        boolean roundCheck;


        //TODO start rounds and turns after this point

        //The order of the index is the play order


        //raison dêtre => Player turn & Round Phase 1 TESTING

        out.println ("\n Round " + roundCount  + " Starting Now!!!\n");

        //X------------------------------------------------------------------Betting
        int betV = 0;
        int betQ = 0;

        //X------------------------------------------------------------------Betting
        //I------------------------------------------------------------------Lying

        String lastPlayerName;
        int lieCount = 0;
        //I------------------------------------------------------------------Lying


        for (int i = 0; i < getPlayerCount (); i++) {
            //I------------------------------------------------------------------Lying
            boolean cantLie = betV <=0 && betQ <= 0;
            //I------------------------------------------------------------------Lying


            Player player = getPlayerList ().get ( i );
            out.println (player.getName () + "'s Turn!\n");

            //I------------------------------------------------------------------Lying
            if (!cantLie){


            }
            //I------------------------------------------------------------------Lying
            //O------------------------------------------------------------------Rolling
            player.rollDice ();
            table.sortDice ( player.getHand () );
            out.println ("You have rolled " + player.getHand ());
            //O------------------------------------------------------------------Rolling
    //X------------------------------------------------------------------Betting
            boolean betCheck = true;
            //X------------------------------------------------------------------Betting
            while (betCheck) {

                out.print ( "Please Bet On The Value Of The Die\nBet: " );
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

            /////////////////////////////////////////////////////////////////////////////
            //X Spacing
            for (int j = 0; j < 10; j++) {
                out.println ("\n<|::||::||::||::||::||::||::||::||::|>\n");
            }
            /////////////////////////////////////////////////////////////////////////////

            }

        //raison dêtre => Tells how many die are on the table
        out.println ("There are " + table.diceInGame +" dice on the table" +"\nThe bets on the table are " + Arrays.deepToString ( table.allBets.toArray () ) );

        //TOdo================== new round stuff
        /////////////////////////////////////////////////////////////////////////////
        //X Spacing
        for (int j = 0; j < 10; j++) {
            out.println ("\n<|::||::||::||::||::||::||::||::||::|>\n");
        }
        /////////////////////////////////////////////////////////////////////////////
        roundCount++;
        out.println ("\n Round " + roundCount + " Starting Now!!!\n");

        }

    }

