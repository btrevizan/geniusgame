package model;

import java.util.ArrayList;


public class Match{

    private Game game;
    private Player player;
    private Sequence sequence;

    public Match(Game game, Player player){
        this.game = game;
        this.player = player;

        int colorsSize = this.game.getColors().size();
        this.sequence = new Sequence(colorsSize);
    }

    /**
     * Gets the player name.
     * @return player name.
     */
    public String getPlayerName(){
        return this.player.getName();
    }

    /**
     * Gets the player points.
     * @return player points.
     */
    public int getPlayerPoints(){
        return this.player.getPoints();
    }

    /**
     * Make a new round of the game.
     */
    public void nextRound(){
        this.sequence.resetIndex();
        this.sequence.push();
        this.play();
    }

    /**
     * Checks if the button pushed is equal to the one in the sequence
     * (checks if the player movement is right).
     * @param button pushed by the player in his move
     * @return false if his play was wrong anda true if it was right.
     */
    public boolean check(int button){
        if(!this.sequence.equals(button))
            return false;

        this.sequence.incIndex();
        return true;
    }

    //acho que isso vai no controller
    //public void gameOver(){
        // ?
    //}

    /**
     * Play the match.
     */
    private void play(){
        double volume = this.game.getVolume();
        int timespan = this.game.getDifficulty();
        ArrayList<SoundedColor> colors = this.game.getColors();

        this.sequence.play(colors, volume, timespan);
    }
}
