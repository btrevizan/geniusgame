package model;

import view.SoundedColor;
import view.View;

import java.util.List;


public class Game{

    private Configuration configuration;
    private View view;
    
    public Game(View view){
        this.setView(view);
        this.configuration = new Configuration(view);
    }

    /**
     * Generates a new game match for a certain player.
     * @param playerName name of the player who will play;
     * @return player match.
     */
    public Match newMatch(String playerName){
        return new Match(this, this.newPlayer(playerName), this.view);
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
     * Gets game colores of game buttons.
     * @return list os colors associated to a sound.
     */
    public List<SoundedColor> getColors(){
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
        this.view.setTimeGap(diff);
    }

    /**
     * Generates a new player.
     * @param name of the player .
     */
    private Player newPlayer(String name){
        return new Player(name);
    }

    public void setView(View view) {
        this.view = view;
    }
}
