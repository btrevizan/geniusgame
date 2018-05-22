import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private ArrayList<Integer> sequence;
    private int sequenceValueRange; // number of states an element of que sequence can be
    private int points;
    private int difficulty;

    public GameModel(int sequenceValueRange, int difficulty){
        this.sequenceValueRange = sequenceValueRange;
        this.sequence = new ArrayList<Integer>();
        this.points = 0;
        this.difficulty = difficulty;
    }

    private void pushRecord(Integer n){
        sequence.add(n);
    }

    private Integer popRecord(){
        return sequence.remove(sequence.size()-1);
    }

    public void newRandomRecord(){
        Random rand = new Random();
        Integer i = rand.nextInt(this.sequenceValueRange);
        this.pushRecord(i);
    }

    public boolean checkSequence(ArrayList<Integer> l){
        if(this.sequence.equals(l)) {
            this.addPoints(this.difficulty);
            return true;
        }else
            return false;
    }

    private void addPoints(int x){
        this.points += x;
    }

    public int getSequenceLength(){
        return this.sequence.size();
    }

    public int getPoints(){
        return this.points;
    }

    // returns a copy so that the model state is not changed
    public ArrayList<Integer> getSequence() {
        return new ArrayList<>(this.sequence);
    }
}
