package controller;

import controller.*;
import model.Game;
import view.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController  {

    private Game game;
    //private Player player;
    private RankingController ranking;
    private View view;

    @FXML Button newGameButton;
    @FXML Button rankingButton;
    @FXML Button settingButton;
    @FXML Button exitButton;

    public MenuController(){
        this.game = new Game();
        //this.player = new Player();
        this.ranking = new RankingController();
        this.view = new View();
    }

    private void handleNewGameButton(ActionEvent event) {
        //um popup ou algo assim pedindo o nome do jogador
            //getNamePlayer()
        //this.game.newMatch();
        //this.view.showArena();

    }

    private void handleRankingButton(ActionEvent event) {
       this.view.showRanking();
       //this.ranking.main();
    }

    private void handleSettingsButton(ActionEvent event) {
        this.view.showSettings();
        //ir pro settingsController
    }


    private void handleExitButton(ActionEvent event) {
        //fechar o jogo
    }


    public void main(){
        newGameButton.setOnAction(this::handleNewGameButton);
        rankingButton.setOnAction(this::handleRankingButton);
        settingButton.setOnAction(this::handleSettingsButton);
        exitButton.setOnAction(this::handleExitButton);

    }
}
