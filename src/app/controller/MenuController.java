package app.controller;

import app.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MenuController{

    @FXML
    private Button newGameButton;

    @FXML
    private Button rankingButton;

    @FXML
    private Button settingsButton;

    @FXML
    public void exit(){
        System.exit(0);
    }

    public Button getNewGameButton(){
        return this.newGameButton;
    }

    public Button getRankingButton(){
        return this.rankingButton;
    }

    public Button getSettingsButton(){
        return this.settingsButton;
    }
}
