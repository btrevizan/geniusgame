package app.model;


public class Game{

    private Configuration configuration;
    
    public Game(){
        this.configuration = new Configuration();
    }

    public Match newMatch(String playerName){
        return new Match(this, this.newPlayer(playerName));
    }

    public Configuration getConfiguration(){
        return this.configuration;
    }

    public void changeVolume(double volume){
        this.configuration.setVolume(volume);
    }

    private Player newPlayer(String name){
        return new Player(name);
    }

    public int getDifficultyPoints(){
        return (int) Math.ceil((Difficulty.HARD / (double) this.configuration.getDifficulty()) * 5);
    }
}
