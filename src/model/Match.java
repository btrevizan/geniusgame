package model;

import java.util.ArrayList;
import view.View;


public class Match{

    private Game game;
    private Player player;
    private Sequence sequence;
    private View view;

    public Match(Game game, Player player, Configuration configuration){
        this.game = game;
        this.player = player;
        this.view = new View();

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
        //tem que mudar aqui os parametros de acordo com a view
	    this.view.showSequence(this.sequence, this.game);
	    this.player.addPoints(this.game.getDifficulty());
	    this.view.updatePoints();
    }

    /**
     * Checks if the game continues based on the gamer play.
     * @param button the button that was last pushed by the player.
     */
    public void check(int button){
    	if(this.sequence.checkTry(button)){
    	    if(this.sequence.getIndex() == 0){
    	    	this.nextRound();
    	    }
    	}else
    	     this.gameOver();
    }

    /**
     * End game.
     */
    public void gameOver(){
        this.game.getRanking().push(this.player);
    	this.view.showRanking();
    }

    /**
     * Play the match.
     */
    private void play(){
        double volume = this.game.getVolume();
        int timespan = this.game.getDifficulty();
        ArrayList<SoundedColor> colors = this.game.getColors();

        this.view.showSequence(this.sequence, this.game);
    }
}
