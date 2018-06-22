package controller;

import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import model.Match;
import view.SoundedColor;

import java.util.ArrayList;

public class MatchController extends ControllerTemplate{

    @FXML
    public Arc blueArc;

    @FXML
    public Arc redArc;

    @FXML
    public Arc greenArc;

    @FXML
    public Arc yellowArc;

    @FXML
    public Text playerName;

    @FXML
    public Text score;

    private Match match;
    private ArrayList<Arc> coloredButtons;

    public MatchController(){
        this.coloredButtons = new ArrayList<>();
        this.coloredButtons.add(blueArc);
        this.coloredButtons.add(redArc);
        this.coloredButtons.add(yellowArc);
        this.coloredButtons.add(greenArc);
    }

    @FXML
    public void initialize(){}

    public void setMatch(Match match){
        this.match = match;
    }

    public void setPlayerName(){
        this.playerName.setText(this.match.getPlayerName());
    }

    public void setScore(){
        this.score.setText(Integer.toString(this.match.getPlayerPoints()));
    }

    public void setColorBrighter(int index, SoundedColor soundedColor){
        Arc arc = this.coloredButtons.get(index);
        arc.setFill(soundedColor.brighter());
    }

    public void setColorDarker(int index, SoundedColor soundedColor){
        Arc arc = this.coloredButtons.get(index);
        arc.setFill(soundedColor.darker());
    }

    public void handleTopLeft(boolean b) {
        if(!b)
            this.triggerClickedButton(0);
    }

    public void handleTopRight(boolean b) {
        if(!b)
            this.triggerClickedButton(1);
    }

    public void handleBottomLeft(boolean b){
        if(!b)
            this.triggerClickedButton(2);
    }

    public void handleBottomRight(boolean b){
        if(!b)
            this.triggerClickedButton(3);
    }

    private void triggerClickedButton(Integer clickedButton) {
        this.match.playFeedback(clickedButton);
        this.match.checkTry(clickedButton);
    }
}
