package com.company;

import java.util.ArrayList;

//When think of table class think of it as table rules
public class Game {

    //Set Structure for all games

    private String name;
    private String rules;
    private byte players;
    private byte rounds;

    private boolean gameCheck;
    private ArrayList<Byte> scoreBoard = new ArrayList<> ();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getRules() {
        return rules;
    }

    public void setPlayers(byte players) {
        this.players = players;
    }

    public byte getPlayers() {
        return players;
    }

    public void setRounds(byte rounds) {
        this.rounds = rounds;
    }

    public byte getRounds() {
        return rounds;
    }

    public void setScoreBoard(ArrayList<Byte> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public ArrayList<Byte> getScoreBoard() {
        return scoreBoard;
    }

    public boolean isGameCheck() {
        return gameCheck;
    }

    public void Start(){
        gameCheck = true;
    }

    public void End(){
        gameCheck = false;
    }
}
