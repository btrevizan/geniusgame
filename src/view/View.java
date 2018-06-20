package view;


import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Game;
import model.Sequence;
import model.SoundedColor;

import java.io.IOException;
import java.util.ArrayList;

public class View extends Application{



    private Stage primaryStage;
    private BorderPane rootLayout;
    private MenuController menu;


    //   @Override
    public void start(Stage primaryStage) throws IOException {
        this.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.show();
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
     * Shows the person the main menu.
     */
    public void showMenu(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("../view/Menu.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Plays the actions to be reproduced avery time a colored button is pushed.
     * @param	volume is the volume in witch the sound of the button pushed will be played.
     */
    public void playButton(SoundedColor soundedColor, double volume){
        soundedColor.color.brighter();
        soundedColor.sound.play(volume);
        soundedColor.color.darker();
    }

    //ver onde que o wait tem que ser usado e por
    /**
     * Creats the time between the plays of a button during a sequence.
     * @param timespan number of the time that the waiting for a new play will last.
     */
    private void wait(int timespan){
        try{
            Thread.sleep(timespan);
        } catch(InterruptedException e) {
            System.err.format("InterruptedException: %s%n", e);
        }
    }

    //ver como fazer isso
    public void updatePoints() {
    }

    /**
     * Shows the players the sequence that he has to memorize.
     */
    public void showSequence(Sequence sequence, Game game){
        ArrayList<SoundedColor> colors = game.getColors();
        double volume = game.getVolume();
        int timespan = game.getDifficulty();

        for(int index: sequence.getSequence()){
            playButton(colors.get(index), volume);
            this.wait(timespan);
        }
    }

    /**
     * Shows the person the settings of the game.
     */
    public void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("/Ranking.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("/Ranking.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the arena of the game.
     */
    public void showArena() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("/NewGame.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
