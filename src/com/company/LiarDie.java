package com.company;

public class LiarDie extends Game {

    private byte valBet;
    private byte quantBet;
    private String name = "Liar's Dice";

    //Set floor  for quantity and value

    public LiarDie(String name, String rules , Byte playSize){

        this.setName ( name );

        this.setRules ( rules );

        this.setPlayers ( playSize );

    }





}
