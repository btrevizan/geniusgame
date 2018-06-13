package model;

import java.awt.Color;
import javafx.scene.media.AudioClip;


public class SoundedColor implements IInstanceFileBased{

    private Color color;
    private AudioClip sound;

    public SoundedColor(Color color, String soundURI){
        this.setColor(color);
        this.setSound(soundURI);
    }

    public SoundedColor(int r, int g, int b, String soundURI){
        this(new Color(r, g, b), soundURI);
    }

    public void setColor(int r, int g, int b){
        this.color = new Color(r, g, b);
    }

    public void setColor(Color color){
        this.color = color;
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

    public static Object createInstance(String[] args){
        int red = Integer.parseInt(args[0]);
        int green = Integer.parseInt(args[1]);
        int blue = Integer.parseInt(args[2]);
        String soundURI = args[3];

        return new SoundedColor(red, green, blue, soundURI);
    }

    public String toString(){
        String red = Integer.toString(this.color.getRed());
        String green = Integer.toString(this.color.getGreen());
        String blue = Integer.toString(this.color.getBlue());
        String soundURI = this.sound.getSource();

        String result;

        result = red + Default.SEPARATOR;
        result = result + green + Default.SEPARATOR;
        result = result + blue + Default.SEPARATOR;
        result = result + soundURI;

        return result;
    }
}
