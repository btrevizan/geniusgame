package model;

import java.util.ArrayList;


public class Configuration{

    private double volume;
    private int difficulty;
    private ArrayList<Color> colors;

    public Configuration(){
        this.setDifficulty(Default.DIFFICULTY);
        this.setVolume(Default.VOLUME);
        this.colors = new ArrayList<Color>();
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    public double getVolume(){
        return this.volume;
    }

    public void setVolume(double volume){
        if(volume < Default.MUTE && volume > Default.VOLUME_HIGH)
            return;

        this.volume = volume;
    }

    public ArrayList<Color> getColors(){
        return (ArrayList<Color>)this.colors.clone();
    }

    public void mute(){
        this.setVolume(Default.MUTE);
    }

    public boolean addColor(Color color){
        return this.colors.add(color);
    }

    public Color removeColor(int index){
        return this.colors.remove(index);
    }
}
