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

    public String getPlayerName(){
        return this.player.getName();
    }

    public int getPlayerPoints(){
        return this.player.getPoints();
    }

    public void nextRound(){
        this.sequence.resetIndex();
        this.sequence.push();
        this.play();
    }

    public boolean check(int button){
        if(!this.sequence.equals(button))
            return false;

        this.sequence.incIndex();
        return true;
    }

    public void gameOver(){
        // ?
    }

    private void play(){
        double volume = this.game.getVolume();
        int timespan = this.game.getDifficulty();
        ArrayList<Color> colors = this.game.getColors();

        this.sequence.play(colors, volume, timespan);
    }
}
