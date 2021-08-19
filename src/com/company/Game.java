package com.company;

import java.util.ArrayList;

//When think of table class think of it as table rules
public abstract class Game {

    //Set Structure for all games

    private String name;
    private String rules;
    private int playerCount;
    private int intialDie;
    private int rounds;
    private ArrayList<Player> playerList = new ArrayList ();
    private boolean gameCheck;
    private ArrayList<Byte> scoreBoard = new ArrayList<> ();

    public Game(String name, String rules, int die) {
        this.name = name;
        this.rules = rules;

        this.intialDie = die;
    }

    public void setGameCheck(boolean gameCheck) {
        this.gameCheck = gameCheck;
    }

    public String getName() {
        return name;
    }


    public int getIntialDie() {
        return intialDie;
    }


    public String getRules() {
        return rules;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getRounds() {
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

    public void addPlayer(Player player){

        playerList.add ( player );

    }

    public void removePlayer(Player player){

        playerList.remove ( playerList.indexOf ( player ) );
    }

    public void Start(){
        gameCheck = true;
    }

    public void End(){
        gameCheck = false;
    }
}
