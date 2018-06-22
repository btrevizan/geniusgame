package controller;

import javafx.fxml.FXML;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import model.Match;
import view.SoundedColor;

import java.util.ArrayList;

public class MatchController extends ControllerTemplate{

    public Arc blueArc;
    public Arc redArc;
    public Arc greenArc;
    public Arc yellowArc;

    @FXML
    public Text playerName;

    @FXML
    public Text score;

    private Match match;
    private ArrayList<Arc> coloredButtons;

    public MatchController(){
        this.coloredButtons = new ArrayList<>();
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

    public void setArcs(){
        this.coloredButtons.add(this.blueArc);
        this.coloredButtons.add(this.redArc);
        this.coloredButtons.add(this.yellowArc);
        this.coloredButtons.add(this.greenArc);
    }

    public void setBlueArc(Arc arc){
        this.blueArc = arc;
    }

    public void setRedArc(Arc arc){
        this.redArc = arc;
    }

    public void setYellowArc(Arc arc){
        this.yellowArc = arc;
    }

    public void setGreenArc(Arc arc){
        this.greenArc = arc;
    }

    public void setColorBrighter(int index, SoundedColor soundedColor){
        Arc arc = this.coloredButtons.get(index);
        arc.setFill(soundedColor.brighter());
    }

    public void setColorDarker(int index, SoundedColor soundedColor){
        Arc arc = this.coloredButtons.get(index);
        arc.setFill(soundedColor.getColor());
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
