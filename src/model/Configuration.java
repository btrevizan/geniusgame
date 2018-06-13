package model;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Configuration{

    private DoubleProperty volume;
    private IntegerProperty difficulty;
    private ObjectProperty<ArrayList<SoundedColor>> colors;

    public Configuration(){
        this.volume = new SimpleDoubleProperty(Default.VOLUME);
        this.difficulty = new SimpleIntegerProperty(Default.DIFFICULTY);

        ArrayList<SoundedColor> color = new ArrayList<SoundedColor>();
        this.colors = new SimpleObjectProperty<ArrayList<SoundedColor>>(colors);
    }

    public int getDifficulty(){
        return this.difficulty.get();
    }

    public void setDifficulty(int difficulty){
        this.difficulty.set(difficulty);
    }

    public double getVolume(){
        return this.volume.get();
    }

    public void setVolume(double volume){
        if(volume < Default.MUTE && volume > Default.VOLUME_HIGH)
            return;

        this.volume.set(volume);
    }

    public ArrayList<SoundedColor> getColors(){
        return this.colors.get();
    }

    public void mute(){
        this.setVolume(Default.MUTE);
    }

    public boolean addColor(SoundedColor color){
        return this.getColors().add(color);
    }

    public SoundedColor removeColor(int index){
        return this.getColors().remove(index);
    }

    // read color file with r,g,b and sounds
}
