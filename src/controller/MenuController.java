package controller;

import model.*;
import view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    private Game game;
    //private Player player;
    private View view;

    @FXML Button newGameButton;
    @FXML Button rankingButton;
    @FXML Button settingButton;
    @FXML Button exitButton;

    private MenuController(){
        this.game = new Game();
        //this.player = new Player();
        this.view = new View();
    }

    private void handleNewGameButton() {
        //um popup ou algo assim pedindo o nome do jogador
            //getNamePlayer()
        this.game.newMatch();
        //ou isso ou ir pro newGameController
        this.view.showArena();
    }

    private void handleRankingButton() {
        this.view.showRanking();
        //ir pro rankingController;
    }

    private void handleSettingsButton() {
        this.view.showSettings();
        //ir pro settingsController
    }


    private void handleExitButton() {

    }

    public void main(){
        newGameButton.setOnAction((event) -> handleNewGameButton());
        rankingButton.setOnAction((event) -> handleRankingButton());
        settingButton.setOnAction((event) -> handleSettingsButton());
        exitButton.setOnAction((event) -> handleExitButton());

    }
}
