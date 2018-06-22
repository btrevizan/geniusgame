package controller;


import javafx.event.ActionEvent;
import model.Game;

public class SettingsController extends ControllerTemplate{

    public SettingsController(Game game){
        this.setGame(game);
    }

    public void handleMuteButton(ActionEvent event){
        this.game.mute();
    }
}
