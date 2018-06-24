package app.model;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;


public class AudioButton implements IInstanceFileBased{

    public String imageBrightURI;
    public String imageDarkURI;
    public AudioClip sound;

    public AudioButton(String brightURI, String darkURI, String soundURI){
        this.setImageBright(brightURI);
        this.setImageDark(darkURI);
        this.setSound(soundURI);
    }

    public void setImageBright(String brightURI){
        File file = new File(brightURI);
        this.imageBrightURI = file.toURI().toString();
    }

    public void setImageDark(String darkURI){
        File file = new File(darkURI);
        this.imageDarkURI = file.toURI().toString();
    }

    private Image getImage(String uri){
        return new Image(uri);
    }

    public Image getImageBright(){
        return this.getImage(this.imageBrightURI);
    }

    public Image getImageDark(){
        return this.getImage(this.imageDarkURI);
    }

    public void setSound(String soundURI){
        File file = new File("media/" + soundURI);
        this.sound = new AudioClip(file.toURI().toString());
    }

    public AudioClip getSound(){
        return this.sound;
    }

    public void play(double volume){
        this.sound.play(volume);
    }

    public static AudioButton createInstance(String[] args){
        String brightURI = args[0];
        String darkURI = args[1];
        String soundURI = args[2];

        return new AudioButton(brightURI, darkURI, soundURI);
    }

    public String toString(){
        String bright = this.imageBrightURI;
        String dark = this.imageDarkURI;
        String sound = this.sound.getSource();

        String result;

        result = bright + Default.SEPARATOR;
        result = result + dark + Default.SEPARATOR;
        result = result + sound;

        return result;
    }
}
