package view;


import controller.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import model.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class View extends Application{



    private Stage primaryStage;
    private BorderPane rootLayout;

    private List<SoundedColor> colors;
    private DoubleProperty volume;
    private IntegerProperty timespan;

    boolean ignoreStatus = false;

    private MenuController menuController = null;
    private MatchController matchController = null;
    private NewGameController newGameController = null;
    private RankingController RankingController = null;
    private SettingsController SettingsController = null;


    //   @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            // Load root layout from fxml file.
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml")));
            Game game = new Game(this);
            this.menuController = new MenuController(game);
            this.matchController = new MatchController(game);
            this.newGameController = new NewGameController(game);
            /*
            this.RankingController = new RankingController(game);
            this.SettingsController = new SettingsController(game);
            */

            // Show the scene containing the root layout.
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            this.initRootLayout();
            this.showMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ver p que que isso sereve se nao for nada tirar
    /**
     * Returns the main stage.
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args){ launch(args); }

    public void setPrimaryStage(Stage primaryStage){
            this.primaryStage = primaryStage;
    }

    /**
     * Initializes the root layout.
     */

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("../view/Main.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the main menu.
     */
    public void showMenu(){
        try {

            AnchorPane menu = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
            Button b = (Button) menu.lookup("#newGameButton");
            b.setOnAction(e -> {
                this.showNewGame(); // @TODO this is triggering exceptions when coming back from NewGame screen
                menuController.handleNewGameButton(e);
            });
            b = (Button) menu.lookup("#rankingButton");
            b.setOnAction(e -> {
                this.showRanking();
                menuController.handleRankingButton(e);
            });
            b = (Button) menu.lookup("#settingsButton");
            b.setOnAction(e -> {
                this.showSettings();
                menuController.handleSettingsButton(e);
            });
            b = (Button) menu.lookup("#exitButton");
            b.setOnAction(e -> {
                menuController.handleExitButton(e);
                this.primaryStage.close();
            });
            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the in-game screen.
     */
    public void showMatch(){
        try {
            AnchorPane match = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Match.fxml"));
            rootLayout.setCenter(match);
            Arc arc = (Arc) match.lookup("#top_left");
            arc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleTopLeft(this.getIgnoreStatus())
            );
            arc = (Arc) match.lookup("#top_right");
            arc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleTopRight(this.getIgnoreStatus())
            );
            arc = (Arc) match.lookup("#bottom_left");
            arc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleBottomLeft(this.getIgnoreStatus())
            );
            arc = (Arc) match.lookup("#bottom_right");
            arc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleBottomRight(this.getIgnoreStatus())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewGame(){
        try {
            AnchorPane newGameScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/NewGame.fxml"));
            rootLayout.setCenter(newGameScreen);
            Button b = (Button) newGameScreen.lookup("#goBackButton");
            b.setOnAction(e -> {
                this.showMenu();
                newGameController.handleGoBackButton(e);
            });
            b = (Button) newGameScreen.lookup("#playButton");
            TextField s = (TextField) newGameScreen.lookup("#tf_playerName");
            b.setOnAction(e -> {
                this.showMatch();
                newGameController.handlePlayButton(e, s.getText());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Plays the actions to be reproduced avery time a colored button is pushed.
     * @param	volume is the volume in witch the sound of the button pushed will be played.
     */
    public void playButton(Integer clickedButton){
        SoundedColor soundedColor = this.colors.get(clickedButton);
        soundedColor.color.brighter();
        soundedColor.sound.play(volume.get());
        soundedColor.color.darker();
        this.waitTime();
    }

    /**
     * Waits n milliseconds.
     * @param n number milliseconds it will wait to proceed execution
     */
    private void waitTime(int n){
        try{
            Thread.sleep(n);
        } catch(InterruptedException e) {
            System.err.format("InterruptedException: %s\n", e);
        }
    }

    /**
     * Time between the feedbacks of a button during a sequence play.
     * @param timespan number of the time that the waiting for a new play will last.
     */
    private void waitTime(){
        this.waitTime(timespan.get());
    }

    //ver como fazer isso
    public void updatePoints() {
    }

    /**
     * Shows the players the sequence that he has to memorize.
     */
    public void showSequence(List<Integer> sequence){
        this.setIgnoreStatus(true);
        this.waitTime();
        System.out.println(sequence.toString());
        for(int index: sequence){
            playButton(index);
            this.waitTime();
        }
        this.setIgnoreStatus(false);
    }

    private void setIgnoreStatus(boolean b) {
        this.ignoreStatus = b;
    }

    /**
     * Shows the person the settings of the game.
     */
    public void showSettings(){
        try {
            AnchorPane menu = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Settings.fxml"));

            List<String> list_of_files = new ArrayList<String>();

            File[] files = new File("./media/").listFiles();
            //If this pathname does not denote a directory, then listFiles() returns null.

            for (File file : files) {
                if (file.isFile()) {
                    list_of_files.add(file.getName());
                }
            }
            System.out.println(list_of_files.toString()); // list_of_files tem uma lista de todos os arquivos de som, adiciona no dropdown daí como opção
            // pode ser o mesmo pra todos, não precisa testar se vai ter 2 iguais

            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the player the game ranking.
     */
    public void showRanking() {
        try {
            AnchorPane ranking = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Ranking.fxml"));
            Button b = (Button) ranking.lookup("#goBackButton");
            b.setOnAction(e -> this.showMenu());
            rootLayout.setCenter(ranking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(double vol){
        this.volume = new SimpleDoubleProperty(vol);
    }

    public void setColors(List<SoundedColor> colors) {
        this.colors = colors;
    }

    public List<SoundedColor> getColors() {
        return new ArrayList<>(colors);
    }

    public void setTimeGap(int timeGap) {
        this.timespan = new SimpleIntegerProperty(timeGap);
    }

    public boolean getIgnoreStatus() {
        return ignoreStatus;
    }
}