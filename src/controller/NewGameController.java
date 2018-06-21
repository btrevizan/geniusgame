package controller;

import javafx.event.ActionEvent;
import model.Game;

public class NewGameController extends ControllerTemplate{

    public NewGameController(Game game){
        this.setGame(game);
    }

    public void handleGoBackButton(ActionEvent e){

    }

    public void handlePlayButton(ActionEvent e, String playername){
        this.game.newMatch(playername);
    }

}
