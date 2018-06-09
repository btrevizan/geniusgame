import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;

public class Sequence{

    private ArrayList<int> sequence;
    private int rangeLimit;
    private Random random;

    public Sequence(int rangeLimit){
        this.sequence = new ArrayList<int>();
        this.rangeLimit = rangeLimit;
        this.random = Random();
    }

    public ArrayList<int> getSequence(){
        return this.sequence.clone();
    }

    public void push(){
        int next = this.nextInt();
        this.sequence.add(next);
    }

    private int nextInt(){
        return this.random.nextInt() % this.rangeLimit;
    }

    public void play(ArrayList<Color> colors, double volume, double timespan){
        for(int index: this.sequence){
            colors.get(index).play(volume);
            Thread.sleep(timespan);
        }
    }
}
