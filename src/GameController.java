public class GameController implements IGameListener{

    private IGameLogic model;

    public GameController(IGamePresenter gp){
        this.model = new GameModel(gp);
    }

    @Override
    public void pushAction(Integer i) {
        this.model.clickedFeedback(i);
        this.model.checkNewTry(i);
    }

    @Override
    public void changeVolume(double newVolume) {
        this.model.setVolume(newVolume);
    }

    @Override
    public void setSoundFX(Integer buttonNumber, String soundURI) {
        this.model.setSoundFX(buttonNumber, soundURI);
    }

    @Override
    public void setPlayerName(String playerName) {
        this.model.setPlayerName(playerName);
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.model.setDifficulty(difficulty);
    }

    @Override
    public void startGame() {
        this.model.startGame();
    }

    @Override
    public void closeGame() {
        this.model.closeGame();
    }
}
