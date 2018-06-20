package view;



import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import model.SoundedColor;
import model.Sequence;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.ArrayList;

public class View {


    private BorderPane rootLayout;

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
     * Shows the person the main menu.
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

    /**
     * Shows the person the main menu.
     */
    public void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("/Menu.fxml"));
            AnchorPane menu = (AnchorPane) loader.load();

            rootLayout.setCenter(menu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
