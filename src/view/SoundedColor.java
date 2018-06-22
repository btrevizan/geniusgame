package view;

import javafx.scene.media.AudioClip;
import model.Default;
import model.IInstanceFileBased;

import javafx.scene.paint.Color;
import java.io.File;


public class SoundedColor implements IInstanceFileBased {

    public Color color;
    public AudioClip sound;

    public SoundedColor(Color color, String soundURI){
        this.setColor(color);
        this.setSound(soundURI);
    }

    public SoundedColor(float r, float g, float b, String soundURI){
        this(new Color(r, g, b, 1.0), soundURI);
    }

    /**
     * Sets Color.
     */
    public void setColor(Color color){
        this.color = color;
    }

    public Color brighter(){
        return this.color.brighter();
    }

    public Color darker(){
        return this.color.darker();
    }

    /**
     * Gets color.
     * @return color that is being used.
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Gets sound.
     * @return sound that is being used.
     */
    public AudioClip getSound(){
        return this.sound;
    }

    /**
     * Sets sound creating a new audio clip based on a string os colors and sound.
     * @param filename string of sound file name. By default it looks into the media folder
     */
    public void setSound(String filename) {
        this.sound = new AudioClip(new File(filename).toURI().toString());

    }

    /**
     * Creates a new object conecting a sound to a color.
     * @param args a array of strings that represents the color in rgb.
     * @return a new sounded colored object.
     */
    public static SoundedColor createInstance(String[] args){
        try {
            float red = Float.parseFloat(args[0]);
            float green = Float.parseFloat(args[1]);
            float blue = Float.parseFloat(args[2]);
            String soundURI = args[3];

            Color color = new Color(red, green, blue, 1.0);
            SoundedColor result = new SoundedColor(color, soundURI);
            return result;
        } catch (Exception e){
            System.err.println(e);
        }

        return null;
    }

    /**
     * Saves the rgb colors ands the soud reated in a big string.
     * @return a string containig the strings os the colors and the sound related to them.
     */
    public String toString(){
        String red = Double.toString(this.color.getRed());
        String green = Double.toString(this.color.getGreen());
        String blue = Double.toString(this.color.getBlue());
        String soundURI = this.sound.toString();

        String result;

        result = red + Default.SEPARATOR;
        result = result + green + Default.SEPARATOR;
        result = result + blue + Default.SEPARATOR;
        result = result + soundURI;

        return result;
    }
}
