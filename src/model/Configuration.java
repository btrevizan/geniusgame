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

    /**
     * Gets difficulty.
     * @return game difficulty.
     */
    public int getDifficulty(){
        return this.difficulty.get();
    }

    /**
     * Sets game difficulty.
     * @param difficulty of the game.
     */
    public void setDifficulty(int difficulty){
        this.difficulty.set(difficulty);
    }

    /**
     * Gets game sound effects volume.
     * @return volume of the game sound effects.
     */
    public double getVolume(){
        return this.volume.get();
    }

    /**
     * Sets game sound effects volume
     * @param volume that game sound effects will be played.
     */
    public void setVolume(double volume){
        if(volume < Default.MUTE && volume > Default.VOLUME_HIGH)
            return;

        this.volume.set(volume);
    }

    /**
     * Gets colors for colored buttons.
     * @return list of colored buttons.
     */
    public ArrayList<SoundedColor> getColors(){
        return this.colors.get();
    }

    /**
     * Sets game colored buttons.
     * @param colors list of game colored buttons.
     */
    private void setColors(ArrayList<SoundedColor> colors){
        this.colors.set(colors);
    }

    /**
     * Mutes all game sound effects.
     */
    public void mute(){
        this.setVolume(Default.MUTE);
    }

    /**
     * Changes game colors of game buttons.
     * @param index of witch colored button the methor is being called for.
     * @param soundURI string with all colores ands sound from colored button.
     */
    public void changeColor(int index, String soundURI){
        ArrayList<SoundedColor> colors = this.getColors();
        colors.get(index).setSound(soundURI);

        this.setColors(colors);
        this.save();
    }

    /**
     * Loads file that has all colores related to their sounds
     */
    public void load(){
        AssetFile file = new AssetFile(this.CONFIGPATH);

        ArrayList<SoundedColor> colors = (ArrayList<SoundedColor>)file.load(SoundedColor.class);
        this.colors.set(colors);
    }

    /**
     * Saves file to store colored buttons settings.
     */
    public void save(){
        AssetFile file = new AssetFile(this.CONFIGPATH);
        file.save(this.getColors());
    }
}
