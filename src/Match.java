public class Match{

    private Game game;
    private int tryIndex;
    private Player player;
    private Sequence sequence;

    public Match(Game game, Player player){
        this.game = game;
        this.player = player;

        int colorsSize = this.game.getColors().size();
        this.sequence = new Sequence(colorsSize);

        this.resetTryIndex();
    }

    public Player getPlayer(){
        return this.player.clone();
    }

    public void nextRound(){
        this.resetTryIndex();
        this.sequence.push();
        this.play();
    }

    public boolean check(int button){
        if(!this.is_correc(button))
            return false; // Game over

        this.incTryIndex();
        return true;
    }

    private void play(){
        double volume = this.game.getVolume();
        double timespan = this.game.getDifficulty();
        ArrayList<Color> colors = this.game.getColors();

        this.sequence.play(colors, volume, timespan);
    }

    public void gameOver(){
        // ?
    }

    private boolean is_correct(int button){
        return this.sequence.get(this.tryIndex) == button;
    }

    private void resetTryIndex(){
        this.tryIndex = 0;
    }

    private void incTryIndex(){
        this.tryIndex = this.tryIndex + 1;
    }
}
