import javafx.scene.media.AudioClip;

public class Color{

    private String color;
    private AudioClip sound;

    public Color(String color, String soundURI){
        this.setColor(color);
        this.setSound(soundURI);
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public AudioClip getSound(){
        return AudioClip.clone();
    }

    public void setSound(String soundURI){
        this.sound = new AudioClip(soundURI);
    }

    public void play(double volume){
        // make it shine
        this.sound.play(volume);
        // make it normal again
    }
}
