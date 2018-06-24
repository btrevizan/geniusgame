package app.controller;

import app.model.Configuration;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

import java.io.File;
import java.util.ArrayList;


public class SettingsController{

    @FXML
    private Slider volumeSlider;

    @FXML
    private ChoiceBox<String> blueAudioBox;

    @FXML
    private ChoiceBox<String> yellowAudioBox;

    @FXML
    private ChoiceBox<String> redAudioBox;

    @FXML
    private ChoiceBox<String> greenAudioBox;

    @FXML
    private Button okButton;

    private Configuration configuration;

    public void setUp(Configuration configuration){
        this.configuration = configuration;

        this.bindVolume();
        this.setChoiceBoxes();
        this.bindBlueAudio();
        this.bindRedAudio();
        this.bindYellowAudio();
        this.bindGreenAudio();
    }

    public Button getOkButton(){
        return this.okButton;
    }

    @FXML
    public void mute(){
        this.configuration.mute();
    }

    @FXML
    public void setDifficultyEasy(){
        this.configuration.setDifficultyEasy();
    }

    @FXML
    public void setDifficultyMedium(){
        this.configuration.setDifficultyMedium();
    }

    @FXML
    public void setDifficultyHard(){
        this.configuration.setDifficultyHard();
    }

    private void setChoiceBoxes(){
        ArrayList<String> songs = new ArrayList<>();
        File[] files = new File("media/").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                songs.add(file.getName());
            }
        }

        ObservableList<String> observableSongs = FXCollections.observableArrayList(songs);

        this.blueAudioBox.setItems(observableSongs);
        this.redAudioBox.setItems(observableSongs);
        this.yellowAudioBox.setItems(observableSongs);
        this.greenAudioBox.setItems(observableSongs);
    }

    private void bindVolume(){
        this.volumeSlider.valueProperty().bindBidirectional(this.configuration.getVolumeProperty());
    }

    private void bindBlueAudio(){
        this.blueAudioBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number old, Number newest) -> {
                    String soundURI = this.blueAudioBox.getItems().get(newest.intValue());
                    this.configuration.changeAudioButton(0, soundURI);
                });
    }

    private void bindRedAudio(){
        this.redAudioBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number old, Number newest) -> {
                    String soundURI = this.redAudioBox.getItems().get(newest.intValue());
                    this.configuration.changeAudioButton(1, soundURI);
                });
    }

    private void bindYellowAudio(){
        this.yellowAudioBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number old, Number newest) -> {
                    String soundURI = this.yellowAudioBox.getItems().get(newest.intValue());
                    this.configuration.changeAudioButton(2, soundURI);
                });
    }

    private void bindGreenAudio(){
        this.greenAudioBox.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observableValue, Number old, Number newest) -> {
                    String soundURI = this.greenAudioBox.getItems().get(newest.intValue());
                    this.configuration.changeAudioButton(3, soundURI);
                });
    }
}
