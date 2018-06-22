package model;
import view.View;



public class Match{

    private Game game;
    private Player player;
    private Sequence sequence;
    private View view;

    public Match(Game game, Player player, View view){
        this.game = game;
        this.player = player;
        this.setView(view);

        this.view.setTimeGap(game.getDifficulty());

        int numberOfColoredButtons = this.game.getColors().size();
        this.sequence = new Sequence(numberOfColoredButtons);

        this.sequence.startUp();
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
	    this.view.showSequence(this.sequence.getSequence());
	    this.player.addPoints(this.getDifficultyPoints());
	    this.view.updatePoints();
    }

    private int getDifficultyPoints(){
        return (int) Math.ceil((Difficulty.HARD / (double) this.game.getDifficulty()) * 5);
    }

    public Sequence getSequence(){
        return this.sequence;
    }

    /**
     * Checks if the game continues based on the gamer play.
     * @param button the button that was last pushed by the player.
     */
    public void checkTry(Integer button){
    	if(this.sequence.checkTry(button)){
    	    if(this.sequence.getIndex() == 0){
    	    	this.nextRound();
    	    }
    	}else
    	     this.matchOver();
    }

    /**
     * End game.
     */
    public void matchOver(){
        this.game.getRanking().push(this.player);
    	this.view.showRanking();
    }


    public void setView(View view) {
        this.view = view;
    }

    public void playFeedback(Integer clickedButton) {
        this.view.playButton(clickedButton);
    }
}
