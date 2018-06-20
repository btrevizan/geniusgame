package controller;


import view.View;
import controller.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RankingController{

    private View view;
    private MenuController menu;
    @FXML Button goBackButton;

    public RankingController(){
        this.view = new View();
        this.menu = new MenuController();
    }

    private void handleGoBackButton(ActionEvent event) {
        this.view.showMenu();
        this.menu.main();
    }

    public void main(){
        goBackButton.setOnAction(this::handleGoBackButton);
    }

}
