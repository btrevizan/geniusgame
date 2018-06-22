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
import model.Match;

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
    private SettingsController settingsController = null;

    private Game game;


    //   @Override
    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            // Load root layout from fxml file.
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml")));
            this.game = new Game(this);
            this.menuController = new MenuController(this.game);
            this.newGameController = new NewGameController(this.game);
            this.settingsController = new SettingsController(this.game);


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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Match.fxml"));
            AnchorPane match = (AnchorPane) loader.load();
            rootLayout.setCenter(match);

            this.matchController = loader.getController();
            Match matchObj = this.game.newMatch(this.newGameController.getPlayerName());
            matchController.setMatch(matchObj);
            matchController.setPlayerName();
            this.updatePoints();

            Arc bluearc = (Arc) match.lookup("#top_left");
            Arc redarc = (Arc) match.lookup("#top_right");
            Arc yellowarc = (Arc) match.lookup("#bottom_left");
            Arc greenarc = (Arc) match.lookup("#bottom_right");

            matchController.setBlueArc(bluearc);
            matchController.setRedArc(redarc);
            matchController.setYellowArc(yellowarc);
            matchController.setGreenArc(greenarc);
            matchController.setArcs();

            bluearc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleTopLeft(this.getIgnoreStatus())
            );

            redarc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleTopRight(this.getIgnoreStatus())
            );

            yellowarc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleBottomLeft(this.getIgnoreStatus())
            );

            greenarc.addEventHandler(MouseEvent.MOUSE_PRESSED,
                    me -> matchController.handleBottomRight(this.getIgnoreStatus())
            );

            //Button endB = (Button) match.lookup("#endGame");
            //endB.setOnAction(e ->  this.showMenu());
            //Button b = (Button) match.lookup("#settings");
            //b.setOnAction(e ->  this.showSettings());

            this.showSequence(matchObj.getSequence().getSequence());

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
                this.newGameController.handleGoBackButton(e);
            });
            b = (Button) newGameScreen.lookup("#playButton");
            TextField s = (TextField) newGameScreen.lookup("#tf_playerName");
            b.setOnAction(e -> {
                this.newGameController.handlePlayButton(e, s.getText());
                this.showMatch();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Plays the actions to be reproduced avery time a colored button is pushed.
     * @param	clickedButton is the volume in witch the sound of the button pushed will be played.
     */
    public void playButton(Integer clickedButton){
        SoundedColor soundedColor = this.colors.get(clickedButton);
        this.matchController.setColorBrighter(clickedButton, soundedColor);
        soundedColor.sound.play(volume.get());
        this.matchController.setColorDarker(clickedButton, soundedColor);
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

    public void updatePoints() {
        this.matchController.setScore();
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
            AnchorPane settings = (AnchorPane) FXMLLoader.load(getClass().getResource("../view/Settings.fxml"));
            rootLayout.setCenter(settings);
            List<String> list_of_files = new ArrayList<String>();

            Button b = (Button) settings.lookup("#okButton");
            b.setOnAction(e ->  this.showMenu());

            Button muteB = (Button) settings.lookup("#muteButton");
            muteB.setOnAction(e ->{
                settingsController.handleMuteButton(e);
            });

            File[] files = new File("./media/").listFiles();
            //If this pathname does not denote a directory, then listFiles() returns null.

            for (File file : files) {
                if (file.isFile()) {
                    list_of_files.add(file.getName());
                }
            }
            System.out.println(list_of_files.toString()); // list_of_files tem uma lista de todos os arquivos de som, adiciona no dropdown daí como opção
            // pode ser o mesmo pra todos, não precisa testar se vai ter 2 iguais

            rootLayout.setCenter(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Shows the player the game ranking.
     */
    public void showRanking() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Ranking.fxml"));
            AnchorPane ranking = (AnchorPane) loader.load();
            Button b = (Button) ranking.lookup("#goBackButton");
            b.setOnAction(e -> this.showMenu());
            rootLayout.setCenter(ranking);

            RankingController controller = loader.getController();
            controller.setModel(this.game);
            controller.setRankingTable();

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
