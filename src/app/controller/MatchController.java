package app.controller;

import app.model.AudioButton;
import app.model.Match;
import app.model.Sequence;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class MatchController{

    @FXML
    private Text playerName;

    @FXML
    private Text score;

    @FXML
    private Button goBackButton;

    @FXML
    private Arc blueArc;

    @FXML
    private Arc redArc;

    @FXML
    private Arc yellowArc;

    @FXML
    private Arc greenArc;

    private Match match;
    private List<Arc> arcs;
    private BooleanProperty gameOverFlag;

    public void setUp(Match match) {
        this.match = match;
        this.gameOverFlag = new SimpleBooleanProperty(false);

        this.setPlayerName();
        this.bindScore();
        this.initArcs();
        this.setArcActions();
        this.addGameOverListener();
        this.addSequenceListener();
    }

    public Button getGoBackButton() {
        return goBackButton;
    }

    public void gameOver(){
        this.gameOverFlag.set(true);
        this.match.gameOver();
    }

    public void startRound(){
        this.disableArcs(true);
        this.playSequence();
        this.disableArcs(false);
    }

    private void disableArcs(boolean disable){
        for(Arc arc: this.arcs){
            arc.setDisable(disable);
        }
    }

    private void playSequence(){
        Sequence sequence = this.match.getSequence();
        ListIterator<Integer> it = sequence.getSequence().listIterator();

        if(!it.hasNext()) return;

        this.wait(1000);
        this.playSequence(it);
    }

    private void playSequence(ListIterator<Integer> it) {
        if(!it.hasNext()) return;

        int index = it.next();
        Timeline timeline = this.playButton(index);
        int timespan = this.match.getGame().getConfiguration().getDifficulty();

        timeline.setOnFinished(e -> {
            this.wait(timespan);
            this.playSequence(it);
        });
    }

    private void wait(int timespan){
        try{
            TimeUnit.MILLISECONDS.sleep(timespan);
        } catch(InterruptedException e) {
            System.err.format("InterruptedException: %s%n", e);
        }

    }

    private void setPlayerName(){
        this.playerName.setText(this.match.getPlayer().getName());
    }

    private void bindScore(){
        IntegerProperty points = this.match.getPlayer().getPointsProperty();
        this.score.textProperty().bind(points.asString());
    }

    private void initArcs(){
        this.arcs = new ArrayList<>();

        this.arcs.add(blueArc);
        this.arcs.add(redArc);
        this.arcs.add(yellowArc);
        this.arcs.add(greenArc);
    }

    private void setArcActions(){
        for(int i = 0; i < this.arcs.size(); i++){
            Arc arc = this.arcs.get(i);
            final int button = i;

            arc.setOnMouseClicked(e -> {
                this.playButton(button);

                if(!this.match.check(button))
                    this.gameOver();
                else
                    this.match.addPoints();
            });
        }
    }

    private void addGameOverListener(){
        this.gameOverFlag.addListener((observable, oldValue, newValue) -> {
            if(newValue) this.goBackButton.fire();
        });
    }

    private void addSequenceListener(){
        this.match.getSequence().getSequence().addListener((observable, oldValue, newValue) -> {
            this.startRound();
        });
    }

    private Timeline playButton(int index){
        double volume = this.match.getGame().getConfiguration().getVolume();
        List<AudioButton> buttons = this.match.getGame().getConfiguration().getButtons();

        buttons.get(index).play(volume);

        Arc arc = this.arcs.get(index);
        Color fill = (Color) arc.getFill();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        KeyValue kv = new KeyValue(arc.fillProperty(), fill.brighter());
        KeyFrame kf = new KeyFrame(Duration.millis(250), kv);

        timeline.getKeyFrames().add(kf);

        timeline.play();
        return timeline;
    }
}

