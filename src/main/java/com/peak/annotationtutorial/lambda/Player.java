package com.peak.annotationtutorial.lambda;

/**
 * @author James Liu
 * @date 11/27/2022 -- 4:42 PM
 */
public  class Player{

    private int assist;
    private int score;
    private int foul;
    private int match ;

    public Player(int assist, int score, int foul, int match) {
        this.assist = assist;
        this.score = score;
        this.foul = foul;
        this.match = match;
    }

    public int getAssist() {
        return assist;
    }

    public int getScore() {
        return score;
    }

    public int getFoul() {
        return foul;
    }

    public int getMatch() {
        return match;
    }
}

