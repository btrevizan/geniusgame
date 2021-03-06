package controller;

import javafx.event.ActionEvent;
import model.Game;

public class NewGameController extends ControllerTemplate{

    private String playerName;

    public NewGameController(Game game){
        this.setGame(game);
    }

    public void handleGoBackButton(ActionEvent e){

    }

    public void handlePlayButton(ActionEvent e, String playerName){
        this.playerName = playerName;
    }

    public String getPlayerName(){
        return this.playerName;
    }

}
