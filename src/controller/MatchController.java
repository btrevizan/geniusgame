package controller;

import model.Game;

public class MatchController extends ControllerTemplate{

    public MatchController(Game game){
        this.setGame(game);
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
        this.game.playFeedback(clickedButton);
        this.game.playRound(clickedButton);
    }
}
