package model;

import java.io.File;
import java.io.IOException;

import java.applet.AudioClip;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound implements AudioClip{

    private String uri;
    private Clip audio;

    public Sound(String soundURI){
        this.uri = soundURI;
        this.load();
    }

    public String getSoundURI(){
        return this.uri;
    }

    public void play(Double volume) {
        this.changeVolume(volume);
        this.play();
    }

    public void play() {
        if(this.audio.isRunning())
            return;

        this.audio.start();
    }

    public void stop() {
        if(!this.audio.isRunning())
            return;

        this.audio.stop();
        this.audio.close();
        this.load();
    }

    public void loop(){
        this.audio.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void changeVolume(Double volume){
        FloatControl control = (FloatControl) this.audio.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(volume.floatValue());
    }

    private void load(){
        try{
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File(this.uri).getAbsoluteFile());

            this.audio = AudioSystem.getClip();
            this.audio.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        } catch (LineUnavailableException e) {
            System.err.println(e);
        }
    }
}
