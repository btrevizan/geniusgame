package model;

import java.awt.Color;
import javafx.scene.media.AudioClip;


public class SoundedColor{

    private OColor color;
    private AudioClip sound;

    public ColoredButton(Color color, String soundURI){
        this.setColor(color);
        this.setSound(soundURI);
    }

    public void setColor(int r, int g, int b){
        this.color = new Color(r, g, b);
    }

    public void setColor(Color color){
        this.color = (Color)color.clone();
    }

    public Color getColor(){
        return this.color;
    }

    public AudioClip getSound(){
        return this.sound;
    }

    public void setSound(String soundURI){
        this.sound = new AudioClip(soundURI);
    }

    public void play(double volume){
        this.color.brighter();
        this.sound.play(volume);
        this.color.darker();
    }
}
