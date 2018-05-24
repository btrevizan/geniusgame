public class GameController implements IGameListener{

    private GameModel model;

    public GameController(GameView gv){
        this.model = new GameModel(gv);
    }

    private void sendTry(Integer theTry){
        this.model.checkNewTry(theTry);
    }


    @Override
    public void pushAction(Integer i) {
        this.model.clickedFeedback(i);
        this.sendTry(i);
    }

    @Override
    public void changeVolume(double newVolume) {
        this.model.setVolume(newVolume);
    }

    @Override
    public void setSoundFX(int buttonNumber, String soundURI) {
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
