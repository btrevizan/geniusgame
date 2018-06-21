package controller;

import model.Game;

public abstract class ControllerTemplate {
    protected Game game;

    public void setGame(Game game){
        this.game = game;
    }
}
