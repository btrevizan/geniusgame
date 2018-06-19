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
	    this.view.showNewSequence(this.sequence.getSequence());
	    this.addPoints(this.difficulty);
	    this.view.updatePoints();
    }


    public boolean check(int button){
    	if(this.sequence.checkTry(button)){
    	    if(this.sequence.getIndex() == 0){
    	    	this.nextRound();
    	    }
    	}else
    	     this.gameOver()
    }

    public void gameOver(){
    	//atualiza o ranking
    	this.view.showRanking();
    }

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
