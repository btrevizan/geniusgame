import java.util.ArrayList;
import java.util.Random;

public class GameModel {

    private ArrayList<Integer> sequence;
    private int sequenceValueRange; // number of states an element of que sequence can be
    private int points;
    private int index;
    private int difficulty;
    private String playerName;
    private GameView view;

    // function for printing and such, will be eliminated later
    private void test(){
        System.out.println("Name: "+this.getPlayerName());
        System.out.println("Difficulty: "+this.getDifficulty());
    }

    public GameModel(GameView view){
        this.view = view;
        this.setVolume(1.0);
        this.newGame();
    }

    private void newGame(){
        this.sequenceValueRange = GameView.NUMBER_OF_BUTTONS;
        this.setSequence(new ArrayList<Integer>());
        this.setPoints(0);
        this.setIndex(0);
    }

    public void startGame(){
        this.pushRandomRecord();
        this.view.setDifficulty(this.getDifficulty());
        this.view.showNewSequence(this.getSequence());
    }

    public void setPlayerName(String playerName){ this.playerName = playerName; }

    private String getPlayerName(){ return this.playerName; }

    private void setDifficulty(int dif){ this.difficulty = dif > 0? dif : 2; }

    public void setDifficulty(String dif){
        int difficulty;
        switch (dif){
            case "easy": difficulty = 1;
                break;
            case "medium": difficulty = 2;
                break;
            case "hard": difficulty = 3;
                break;
            default: difficulty = 2;
        }
        this.setDifficulty(difficulty);
    }

    private int getDifficulty(){ return this.difficulty; }

    private void incIndex(){ this.index++; }

    private void setIndex(int i){ this.index = i; }

    private int getIndex(){ return this.index; }

    private void setPoints(int pts){ this.points = pts; }

    private void addPoints(int x){ this.points += x; }

    public void setSoundFX(Integer buttonNumber, String soundURI) {
        this.view.getColoredButton(buttonNumber).setClickSound(soundURI);
    }

    public void setVolume(double x){
        if(x > 1.0)
            x = 1.0;
        else if(x < 0)
            x = 0.0;

        this.view.setVolume(x);
    }

    private void pushRecord(Integer n){
        sequence.add(n);
    }

    private Integer popRecord(){
        return sequence.remove(sequence.size()-1);
    }

    private void pushRandomRecord(){
        Random rand = new Random();
        Integer i = rand.nextInt(this.sequenceValueRange);
        this.pushRecord(i);
        System.out.println("Sequence: " + this.sequence.toString());
    }

    public void checkNewTry(Integer theTry){
        Integer nextSequenceElement = sequence.get(this.getIndex());


        if(theTry.equals(nextSequenceElement)) {
            this.incIndex();
            if(this.index == this.getSequenceLength()) {
                this.setIndex(0);
                this.pushRandomRecord();
                this.addPoints(this.getDifficulty());
                this.view.showNewSequence(this.getSequence());
                this.view.showNewPoints(this.getPoints());
            }

        }else
            this.gameOver();
    }

    public void clickedFeedback(Integer clickedButton){
        this.view.showClickedButton(clickedButton);
    }

    private void gameOver(){
        // todo: save if top 10 (a method in model)
        this.sequence = new ArrayList<>();
        this.setIndex(0);
        this.view.gameOver();
    }

    public void closeGame(){
        // todo: save if top 10 (a method in model)
    }

    private int getSequenceLength(){ return this.sequence.size(); }

    private int getPoints(){ return this.points; }

    private void setSequence(ArrayList<Integer> ns){ this.sequence = ns; }

    // returns a copy so that the model state is not changed
    private ArrayList<Integer> getSequence() { return new ArrayList<>(this.sequence); }
}
