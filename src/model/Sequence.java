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

        this.resetIndex();
    }

    /**
     * Gets the sequence
     * @return the sequence of plays.
     */
    public ArrayList<Integer> getSequence(){
        return new ArrayList<Integer>(this.sequence);
    }

    /**
     * Gets the index of in where the sequence is.
     * @return the number on the index of sequence
     */
    public int getNextElement(){
        return this.sequence.get(this.getIndex());
    }

    /**
     * Gets index.
     * @return the index as a int;
     */
    public int getIndex(){
    	return this.index;
    }

    /**
     * Resets index.
     */
    private void resetIndex(){
        this.index = 0;
    }

    /**
     * Increase index.
     */
    private void incIndex(){
        this.index = this.index + 1;
    }

    /**
     * Pushes a new number to the sequence adding a new play to the game.
     */
    public void push(Integer n){
        this.sequence.add(n);
    }

    /**
     * Pushes a new random number to the sequence adding a new play to the game.
     */
    public void pushRandom(){
    	Random rand = new Random();
    	Integer i = rand.nextInt(this.getRangeLimit());
    	this.push(i);
    }

    /**
     * Gets range limit.
     */
    private int getRangeLimit(){
    	return this.rangeLimit;
    }

    /**
     * Try if the player pushed de right button and if so it prepares the sequence to the new round
     * @param theTry Interger that represents the button pushed by the player
     * @return false if the player got the sequence wrong and true if the player got the sequence right
     */
    public boolean checkTry(Integer theTry){
        Boolean isEqual =  theTry.equals(this.getNextElement());
        
        if(isEqual){
        	this.incIndex();
        	if(this.index == this.sequence.size()){
                this.pushRandom();
                this.resetIndex();
        	}        	
        	return true;        	
        }
        return false;
    }
}
