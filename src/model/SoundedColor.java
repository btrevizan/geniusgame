package model;

import java.awt.Color;
import javafx.scene.media.AudioClip;


public class SoundedColor implements IInstanceFileBased{

    public Color color;
    public AudioClip sound;

    public SoundedColor(Color color, String soundURI){
        this.setColor(color);
        this.setSound(soundURI);
    }

    public SoundedColor(int r, int g, int b, String soundURI){
        this(new Color(r, g, b), soundURI);
    }

    /**
     * Creates a new color based on the parameters.
     * @param r interger that represents red in the making of the color
     * @param g interger that represents green in the making of the color
     * @param b interger that represents blue in the making of the color
     */
    public void setColor(int r, int g, int b){
        this.color = new Color(r, g, b);
    }

    /**
     * Sets Color.
     */
    public void setColor(Color color){
        this.color = color;
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
     * @param soundURI string of color os rgb and the sound related to that color
     */
    public void setSound(String soundURI){
        this.sound = new AudioClip(soundURI);
    }

    /**
     * Creates a new object conecting a sound to a color.
     * @param args a array of strings that represents the color in rgb.
     * @return a new sounded colored object.
     */
    public static Object createInstance(String[] args){
        int red = Integer.parseInt(args[0]);
        int green = Integer.parseInt(args[1]);
        int blue = Integer.parseInt(args[2]);
        String soundURI = args[3];

        return new SoundedColor(red, green, blue, soundURI);
    }

    /**
     * Saves the rgb colors ands the soud reated in a big string.
     * @return a string containig the strings os the colors and the sound related to them.
     */
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
