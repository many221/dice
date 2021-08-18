package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.*;

public class LiarDie extends Game {

    private int vBet;

    private int qBet;
    //X ==> Move bets to within the round

    //This data needs to only last a turn
    //TODO: Make turn System ===[]
    //TODO: Make diceGame size change when dice removed ===[]
    //TODO: Keep Dice Seperate Enough to figure out winner ===[]
    //TODO: Keep bets player specific ===[]
    //TODO: Be able to compare bets and find winner based off of conditionals ===[]
    //TODO: Make bet conditional ===[]
    //TODO: Make Winner Conditonal ===[]

    private ArrayList<ArrayList<Integer>> diceInGame = new ArrayList<> ();

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

    public void placeBet(){

        out.print ("\n There are " +( getDiceInGame ().size () * getDiceInGame ().get ( 0 ).size ())+
                "Please place a bet on the value 1-6\nBet: ");
        vBet = UI.num ();
        out.print("Please place a bet on the Quantity 1-5\nBet: ");
        qBet = UI.num ();

    }

    public int getvBet() {
        return vBet;
    }

    public int getqBet() {
        return qBet;
    }

    public ArrayList<ArrayList<Integer>> getDiceInGame() {
        return diceInGame;
    }



    @Override
    public void Start() {
        super.Start ();

        //TODO: Turn/Round system ===[]
//X> THEY ARE JUST NESTED LOOPS!!!!!!!!!!!
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

        //X=> store any variables to be monitored from rounds here

        int roundCount;

        boolean roundCheck;


        //X=> start rounds and turns after this point

        //raison dêtre => Testing how a turn would play out
        for (int i = 0; i < getPlayerCount (); i++) {

            Player player = getPlayerList ().get ( i );
            out.println (player.getName ());
            player.rollDice ();
            getDiceInGame ().add ( player.getHand () );
            out.println ("You have rolled " + player.getHand ());
            placeBet ();
            out.println ("Your Bet Is That The Value Of The Dice is " + getvBet () + " and the quantity is " + getqBet () );


        }
        //raison dêtre => Tells how many die are on the table
        out.println ("The die on the table are " + getDiceInGame () );

    }
}
