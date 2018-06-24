package app.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;


public class Configuration implements IFileBased {

    private DoubleProperty volume;
    private IntegerProperty difficulty;
    private List<AudioButton> buttons;

    private static final String AUDIOPATH = "resources/sounded_color.txt";

    public Configuration(){
        this.volume = new SimpleDoubleProperty();
        this.difficulty = new SimpleIntegerProperty();

        this.setVolume(Default.VOLUME);
        this.setDifficulty(Default.DIFFICULTY);

        this.buttons = new ArrayList<>();
        this.load();
    }

    public void setVolume(double volume){
        if(volume < Default.MUTE)
            this.mute();
        else if(volume > Default.VOLUME_HIGH)
            this.volume.set(Default.VOLUME_HIGH);
        else
            this.volume.set(volume);
    }

    public double getVolume(){
        return this.volume.get();
    }

    public DoubleProperty getVolumeProperty(){
        return this.volume;
    }

    private void setDifficulty(int difficulty) {
        this.difficulty.set(difficulty);
    }

    public int getDifficulty() {
        return difficulty.get();
    }

    public IntegerProperty getDifficultyProperty(){
        return this.difficulty;
    }

    public void setDifficultyEasy(){
        this.setDifficulty(Difficulty.EASY);
    }

    public void setDifficultyMedium(){
        this.setDifficulty(Difficulty.MEDIUM);
    }

    public void setDifficultyHard(){
        this.setDifficulty(Difficulty.HARD);
    }

    private void setButtons(List<AudioButton> buttons){
        this.buttons = buttons;
    }

    public List<AudioButton> getButtons(){
        return this.buttons;
    }

    public void mute(){
        this.setVolume(Default.MUTE);
    }

    public void changeAudioButton(int index, String soundURI){
        this.buttons.get(index).setSound(soundURI);
    }

    public void load(){
        AssetFile file = new AssetFile(Configuration.AUDIOPATH);

        List<AudioButton> buttons = (List<AudioButton>) file.load(AudioButton.class);
        this.setButtons(buttons);
    }

    public void save(){
        AssetFile file = new AssetFile(Configuration.AUDIOPATH);
        file.save(this.getButtons());
    }
}
