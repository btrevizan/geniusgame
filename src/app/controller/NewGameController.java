package app.controller;

import app.model.Game;
import app.model.Match;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewGameController{

    @FXML
    private TextField playerNameInput;

    @FXML
    private Button goBackButton;

    @FXML
    private Button playButton;

    private Game game;

    public void setModel(Game game){
        this.game = game;
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Match play(){
        String playerName = playerNameInput.getText();
        return this.game.newMatch(playerName);
    }
}
