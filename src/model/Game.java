package model;

import java.util.ArrayList;


public class Game{

    private Configuration configuration;

    public Game(){
        this.configuration = new Configuration();
    }

    public Match newMatch(String playerName){
        return new Match(this, this.newPlayer(playerName));
    }

    public int getDifficulty(){
        return this.configuration.getDifficulty();
    }

    public Ranking getRanking(){
        return new Ranking();
    }

    public double getVolume(){
        return this.configuration.getVolume();
    }

    public ArrayList<SoundedColor> getColors(){
        return this.configuration.getColors();
    }

    public void changeVolume(double vol){
        this.configuration.setVolume(vol);
    }

    public void mute(){
        this.configuration.mute();
    }

    public void setDifficultyEasy(){
        this.changeDifficulty(Difficulty.EASY);
    }

    public void setDifficultyMedium(){
        this.changeDifficulty(Difficulty.MEDIUM);
    }

    public void setDifficultyHard(){
        this.changeDifficulty(Difficulty.HARD);
    }
    private void changeDifficulty(int diff){
        this.configuration.setDifficulty(diff);
    }

    private Player newPlayer(String name){
        return new Player(name);
    }

}
