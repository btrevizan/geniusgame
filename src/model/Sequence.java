import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;

public class Sequence{

    private ArrayList<Integer> sequence;
    private int rangeLimit;
    private Random random;

    public Sequence(int rangeLimit){
        this.sequence = new ArrayList<Integer>();
        this.rangeLimit = rangeLimit;
        this.random = new Random();
    }

    public ArrayList<Integer> getSequence(){
        return (ArrayList<Integer>)this.sequence.clone();
    }

    public void push(){
        int next = this.nextInt();
        this.sequence.add(next);
    }

    private int nextInt(){
        return this.random.nextInt() % this.rangeLimit;
    }

    public void play(ArrayList<Color> colors, double volume, int timespan){
        for(int index: this.sequence){
            colors.get(index).play(volume);

            try{
                Thread.sleep(timespan);
            } catch(InterruptedException e) {
                System.err.format("InterruptedException: %s%n", e);
            }
        }
    }
}
