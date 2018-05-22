import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.event.EventHandler;

public class ColoredButton extends Button {

    AudioClip clickSound;

    public ColoredButton(String name, String soundfx, EventHandler handler){
        super(name);
        this.clickSound = new AudioClip(soundfx);
        this.setOnAction(handler);
    }

    public void onClicked(double volume){
        this.clickSound.play(volume);
        // make button shine
    }
}
