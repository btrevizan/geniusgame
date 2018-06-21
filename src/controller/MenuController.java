package controller;

import javafx.event.ActionEvent;
import model.Game;

public class MenuController extends ControllerTemplate {

    private Game game;
    //private Player player;


    public MenuController(Game game){
        this.setGame(game);
    }

    public void handleNewGameButton(ActionEvent event) {
    }

    public void handleRankingButton(ActionEvent event) {
       //this.ranking.main();
    }

    public void handleSettingsButton(ActionEvent event) {
        //ir pro settingsController
    }

    // @TODO implementation
    public void handleExitButton(ActionEvent event) {
        //fechar o jogo
    }

}
