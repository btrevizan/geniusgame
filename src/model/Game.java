package model;

import java.util.ArrayList;


public class Game{

    private Configuration configuration;

    public Game(){
        this.configuration = new Configuration();
    }

    /**
     * Generates a new game match for a certain player.
     * @param playerName name of the player who wil play;
     * @return player match.
     */
    public Match newMatch(String playerName){
        return new Match(this, this.newPlayer(playerName), this.configuration);
    }

    /**
     * Gets difficulty of the game.
     * @return difficulty od the game match.
     */
    public int getDifficulty(){
        return this.configuration.getDifficulty();
    }

    /**
     * Gets game ranking.
     * @return game ranking
     */
    public Ranking getRanking(){
        return new Ranking();
    }

    /**
     * Gets game sound effects volume.
     * @return game sound effects volume.
     */
    public double getVolume(){
        return this.configuration.getVolume();
    }

    /**
     * Gets game colores of game buttons.
     * @return list os colors associated to a sound.
     */
    public ArrayList<SoundedColor> getColors(){
        return this.configuration.getColors();
    }

    /**
     * Changes volume to vol.
     * @param vol double chosen by player or default.
     */
    public void changeVolume(double vol){
        this.configuration.setVolume(vol);
    }

    /**
     * Mutes game sound effects.
     */
    public void mute(){
        this.configuration.mute();
    }

    /**
     * Sets game difficulty as easy.
     */
    public void setDifficultyEasy(){
        this.changeDifficulty(Difficulty.EASY);
    }

    /**
     * Sets game difficulty as medium.
     */
    public void setDifficultyMedium(){
        this.changeDifficulty(Difficulty.MEDIUM);
    }

    /**
     * Sets game difficulty as hard.
     */
    public void setDifficultyHard(){
        this.changeDifficulty(Difficulty.HARD);
    }

    /**
     * Changes game difficullty to diff.
     * @param diff dificultty chosen by player or default.
     */
    private void changeDifficulty(int diff){
        this.configuration.setDifficulty(diff);
    }

    /**
     * Generates a new player.
     * @param name of the player .
     */
    private Player newPlayer(String name){
        return new Player(name);
    }

}
