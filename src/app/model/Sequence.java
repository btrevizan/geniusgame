package app.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Thread;


public class Sequence{

    private ListProperty<Integer> sequence;
    private int rangeLimit;
    private Random random;
    private int index;

    public Sequence(){
        this(Default.RANGE_LIMIT);
    }

    public Sequence(int rangeLimit){
        this.sequence = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.rangeLimit = rangeLimit;
        this.random = new Random();

        this.resetIndex();
        this.push();
    }

    public ListProperty<Integer> getSequence(){
        return this.sequence;
    }

    public int getIndex() {
        return this.index;
    }

    public int getFromIndex(){
        return this.sequence.get(this.index);
    }

    public void resetIndex(){
        this.index = 0;
    }

    public void incIndex(){
        if(this.sequence.size() == 0) return;
        this.index = (this.index + 1) % this.sequence.size();
    }

    public void push(){
        int next = this.nextInt();
        this.sequence.add(next);
    }

    public boolean equals(int button){
        try {
            return this.getFromIndex() == button;
        } catch (IndexOutOfBoundsException e){
            return true;
        }
    }

    private int nextInt(){
        return this.random.nextInt(this.rangeLimit);
    }
}