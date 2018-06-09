public class Game{

    private Ranking ranking;
    private Configuration configuration;

    public Game(){
        this.ranking = new Ranking();
        this.configuration = new Configuration();
    }

    public Match newMatch(){
        return new Match(this, this.newPlayer());
    }

    public double getDifficulty(){
        return this.configuration.getDifficulty();
    }

    public Ranking getRanking(){
        return this.ranking.clone();
    }

    public double getVolume(){
        return this.configuration.getVolume();
    }

    public ArrayList<Color> getColors(){
        return this.configuration.getColors();
    }

    public void changeVolume(double vol){
        this.configuration.setVolume(vol);
    }

    public void mute(){
        this.configuration.mute();
    }

    public void setDifficultyEasy(){
        this.changeDifficulty(Difficulty.EASY);
    }

    public void setDifficultyMedium(){
        this.changeDifficulty(Difficulty.MEIDUM);
    }

    public void setDifficultyHard(){
        this.changeDifficulty(Difficulty.HARD);
    }
    private void changeDifficulty(int diff){
        this.configuration.setDifficulty(diff);
    }

    private Player newPlayer(String name){
        return Player(name);
    }

}
