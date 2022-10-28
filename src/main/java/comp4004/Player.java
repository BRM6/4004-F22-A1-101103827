package comp4004;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;


    //player properties
    private String name;
    private int score;
    private int round;
    private String fortuneCard;
    private String[] holdingDie = new String[8];

    private String[] currentRoll = new String[8];
    private boolean isLastRound = false;


    //player constructor
    public Player(String n){
        name = n;
        score = 0;
        round = 1;
        fortuneCard = "";
    }


    public String[] getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(String[] currentRoll) {
        this.currentRoll = currentRoll;
    }

    //getter for name
    public String getName() {
        return name;
    }

    //getter setter for score
    public int getScore() {
        return score;
    }
    public void setScore(int score){
        this.score = score;
        if (this.score < 0){
            this.score = 0;
        }
    }

    public void nextRound(){
        round++;
    }

    //getter for holdingDie
    public String[] getHoldingDie(){
        return holdingDie;
    }

    public String[] emptyHoldingDie(){
        return new String[8];
    }

    public String[] setHoldingDie(String[] hold){
        holdingDie = hold;
        return holdingDie;
    }

    public String getFortuneCard(){
        return fortuneCard;
    }
    public void setFortuneCard(String card){
        fortuneCard = card;
    }

}