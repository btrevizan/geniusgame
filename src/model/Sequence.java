package model;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;


public class Sequence{

    private ArrayList<Integer> sequence;
    private int rangeLimit;
    private Random random;
    private int index;

    public Sequence(){
        this(Default.RANGE_LIMIT);
    }

    public Sequence(int rangeLimit){
        this.sequence = new ArrayList<Integer>();
        this.rangeLimit = rangeLimit;
        this.random = new Random();

        this.resetIndex();
    }

    public ArrayList<Integer> getSequence(){
        return (ArrayList<Integer>)this.sequence.clone();
    }

    public int getFromIndex(){
        return this.sequence.get(this.index);
    }

    public void resetIndex(){
        this.index = 0;
    }

    public void incIndex(){
        this.index = this.index + 1;
    }

    public void push(){
        int next = this.nextInt();
        this.sequence.add(next);
    }

    public void play(ArrayList<SoundedColor> colors, double volume, int timespan){
        for(int index: this.sequence){
            colors.get(index).play(volume);
            this.wait(timespan);
        }
    }

    public boolean equals(int button){
        return this.getFromIndex() == button;
    }

    private int nextInt(){
        return this.random.nextInt(this.rangeLimit);
    }

    private void wait(int timespan){
        try{
            Thread.sleep(timespan);
        } catch(InterruptedException e) {
            System.err.format("InterruptedException: %s%n", e);
        }
    }
}
