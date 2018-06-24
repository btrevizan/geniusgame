package app.model;


import java.util.List;

public class Match{

    private Game game;
    private Player player;
    private Sequence sequence;

    public Match(Game game, Player player){
        this.game = game;
        this.player = player;
        this.sequence = new Sequence(Default.RANGE_LIMIT);
    }

    public Game getGame() {
        return this.game;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Sequence getSequence() {
        return this.sequence;
    }

    public void nextRound(){
        this.sequence.push();
    }

    public boolean check(int button){
        if(!this.sequence.equals(button))
            return false;

        this.incIndex();
        return true;
    }

    private void incIndex(){
        this.sequence.incIndex();
        if(this.sequence.getIndex() == 0) this.nextRound();
    }

    public void gameOver(){
        Ranking ranking = new Ranking();
        ranking.push(this.player);
    }

    public void addPoints(){
        int points = this.game.getDifficultyPoints();
        this.player.addPoints(points);
    }
}