package com.company;

import java.util.ArrayList;

public class LiarDie extends Game {

//    public LiarDie(String name, String rules , Byte playSize){
//
//        this.setName ( name );
//
//        this.setRules ( rules );
//
//        this.setPlayers ( playSize );
//
//    }
    private byte valueBet;

    private byte quantityBet;

    public LiarDie(){
       super.setName ( "Lair's Die" );
       super.setRules ( """
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
               """);

    }

    public void placeBet(){
        System.out.println ("Please place a bet on the value 1-6\nBet: ");

    }





}
