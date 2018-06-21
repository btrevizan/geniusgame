package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Game;

public class RankingController{

    @FXML Button goBackButton;

    Game model;

    RankingController(Game model){ this.setModel(model); }

    private void handleGoBackButton(ActionEvent event) {
        ;
    }

    public void main(){
        goBackButton.setOnAction(this::handleGoBackButton);
    }

    public void setModel(Game model) {
        this.model = model;
    }
}
