package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import view.SoundedColor;
import view.View;

import java.util.ArrayList;
import java.util.List;


public class Configuration implements IFileBased {

    private IntegerProperty difficulty;
    View view;

    private static final String CONFIGPATH = "assets/sounded_color.txt";

    public Configuration(View view){
        this.view = view;
        this.view.setVolume(Default.VOLUME);
        this.setDifficulty(Default.DIFFICULTY);

        ArrayList<SoundedColor> colors = new ArrayList<SoundedColor>();
        this.view.setColors(new ArrayList<SoundedColor>(colors));

        this.load();
    }

    /**
     * Sets game sound effects volume
     * @param volume that game sound effects will be played.
     */
    public void setVolume(double volume){
        if(volume < Default.MUTE)
            this.view.setVolume(Default.MUTE);
        else if(volume > Default.VOLUME_HIGH)
            this.view.setVolume(Default.VOLUME_HIGH);
        else
            this.view.setVolume(volume);
    }

    /**
     * Gets colors for colored buttons.
     * @return list of colored buttons.
     */
    public List<SoundedColor> getColors(){
        return this.view.getColors();
    }

    /**
     * Sets game colored buttons.
     * @param colors list of game colored buttons.
     */
    private void setColors(List<SoundedColor> colors){
        this.view.setColors(colors);
    }

    /**
     * Mutes all game sound effects.
     */
    public void mute(){
        this.setVolume(Default.MUTE);
    }

    /**
     * Changes game colors of game buttons.
     * @param index of witch colored button the method is being called for.
     * @param soundURI string with all sound from colored button.
     */
    public void changeColor(int index, String soundURI){
        List<SoundedColor> colors = this.getColors();
        colors.get(index).setSound(soundURI);

        this.setColors(colors);
        this.save();
    }

    /**
     * Loads file that has all colors related to their sounds
     */
    public void load(){
        AssetFile file = new AssetFile(Configuration.CONFIGPATH);

        List<SoundedColor> colors = (List<SoundedColor>)file.load(SoundedColor.class);
        this.view.setColors(colors);
    }

    /**
     * Saves file to store colored buttons settings.
     */
    public void save(){
        AssetFile file = new AssetFile(Configuration.CONFIGPATH);
        file.save(this.getColors());
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = new SimpleIntegerProperty(difficulty);
    }

    public Integer getDifficulty() {
        return difficulty.get();
    }
}
