package model;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Configuration implements IFileBased{

    private DoubleProperty volume;
    private IntegerProperty difficulty;
    private ObjectProperty<ArrayList<SoundedColor>> colors;

    private final String CONFIGPATH = "../assets/sounded_colors.txt";

    public Configuration(){
        this.volume = new SimpleDoubleProperty(Default.VOLUME);
        this.difficulty = new SimpleIntegerProperty(Default.DIFFICULTY);

        ArrayList<SoundedColor> colors = new ArrayList<SoundedColor>();
        this.colors = new SimpleObjectProperty<ArrayList<SoundedColor>>(colors);

        this.load();
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

    private void setColors(ArrayList<SoundedColor> colors){
        this.colors.set(colors);
    }

    public void mute(){
        this.setVolume(Default.MUTE);
    }

    public void changeColor(int index, String soundURI){
        ArrayList<SoundedColor> colors = this.getColors();
        colors.get(index).setSound(soundURI);

        this.setColors(colors);
        this.save();
    }

    public void load(){
        AssetFile file = new AssetFile(this.CONFIGPATH);

        ArrayList<SoundedColor> colors = (ArrayList<SoundedColor>)file.load(SoundedColor.class);
        this.colors.set(colors);
    }

    public void save(){
        AssetFile file = new AssetFile(this.CONFIGPATH);
        file.save(this.getColors());
    }
}
