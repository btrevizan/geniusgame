package model;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;


public class Sequence{

    private ArrayList<Integer> sequence;
    private int rangeLimit;
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

    /**
     * Gets the sequence
     * @return the sequence of plays.
     */
    public ArrayList<Integer> getSequence(){
        return (ArrayList<Integer>)this.sequence.clone();
    }

    /**
     * Gets the index of in where the sequence is.
     * @return the number on the index of sequence
     */
    public int getFromIndex(){
        return this.sequence.get(this.index);
    }

    /**
     * Resets index.
     */
    public void resetIndex(){
        this.index = 0;
    }

    /**
     * Increase index.
     */
    public void incIndex(){
        this.index = this.index + 1;
    }

    /**
     * Pushes a new number to teh sequence adding a new play to the game.
     */
    public void push(){
        int next = this.nextInt();
        this.sequence.add(next);
    }


    public boolean checkTry(Interger theTry){
    	//aqui tem que mudar a comparação
        isEqual =  this.getFromIndex();
        
        if(isEqual){
        	this.sequence.incIndex();
        	if(this.sequence.index == this.sequence.size()){
                this.push();
                this.resetIndex();
        	}        	
        	return true;        	
        }
        return false;
    }

    /**
     * Generates the new int to be added in the sequnce.
     * @return a random number between the range limit.
     */
    private int nextInt(){
        return this.random.nextInt(this.rangeLimit);
    }
    
    /**
     * Plays the button that is indicated by index.
     * @param colors list of colors conected to their sounds
     * @param volume volume of the sound of the button
     * @param timespan time between button plays
     */
    public void play(ArrayList<SoundedColor> colors, double volume, int timespan){
        for(int index: this.sequence){
            colors.get(index).play(volume);
            this.wait(timespan);
        }
    }

    /**
     * Creats the time between the plays of a button during a sequence.
     * @param timespan number of the time that the waiting for a new play will last.
     */
    private void wait(int timespan){
        try{
            Thread.sleep(timespan);
        } catch(InterruptedException e) {
            System.err.format("InterruptedException: %s%n", e);
        }
    }
}
