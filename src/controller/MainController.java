package controller;

import controller.MenuController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.View;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainController extends Application{


    private Stage primaryStage;
    private MenuController menu;
    private View view;


    //   @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.view = new View();
        this.view.setPrimaryStage(primaryStage);
        this.menu = new MenuController();

        this.view.initRootLayout();

        this.view.showMenu();
        this.menu.main();

    }
    //ver p que que isso sereve se nao for nada tirar
    /**
     * Returns the main stage.
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args){
        launch(args);
    }
}
