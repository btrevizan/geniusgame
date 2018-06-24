package app;

import app.controller.*;
import app.model.Default;
import java.io.IOException;

import app.model.Game;
import app.model.Match;
import app.model.Ranking;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        this.game = new Game();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Default.GAME_NAME);

        this.initRootLayout();
        this.showMenu();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
            this.rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMenu() {
        FXMLLoader loader = this.show("view/Menu.fxml");
        MenuController menu = loader.getController();

        menu.getNewGameButton().setOnAction(e -> {
            this.showNewGame();
        });

        menu.getRankingButton().setOnAction(e -> {
            this.showRanking();
        });

        menu.getSettingsButton().setOnAction(e -> {
            this.showSettings();
        });
    }

    public void showNewGame() {
        FXMLLoader loader = this.show("view/NewGame.fxml");
        NewGameController newGame = loader.getController();

        newGame.setModel(this.game);

        newGame.getGoBackButton().setOnAction(e -> {
            this.showMenu();
        });

        newGame.getPlayButton().setOnAction(e -> {
            Match match = newGame.play();
            this.showMatch(match);
        });
    }

    public void showMatch(Match match){
        FXMLLoader loader = this.show("view/Match.fxml");
        MatchController matchController = loader.getController();

        matchController.setUp(match);

        matchController.getGoBackButton().setOnAction(e -> {
            matchController.gameOver();
            this.showRanking();
        });

        matchController.startRound();
    }

    public void showRanking() {
        FXMLLoader loader = this.show("view/Ranking.fxml");
        RankingController ranking = loader.getController();

        ranking.getBackButton().setOnAction(e -> {
            this.showMenu();
        });

        ranking.setModel(new Ranking());
        ranking.setRankingTable();
    }

    public void showSettings() {
        FXMLLoader loader = this.show("view/Settings.fxml");
        SettingsController settings = loader.getController();

        settings.setUp(this.game.getConfiguration());

        settings.getOkButton().setOnAction(e -> {
            this.showMenu();
        });
    }

    public FXMLLoader show(String name){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(name));
            AnchorPane pane = loader.load();

            this.rootLayout.setCenter(pane);
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}