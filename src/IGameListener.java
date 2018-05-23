public interface IGameListener {
    void pushAction(Integer i);
    void changeVolume(double newVolume);
    void setSoundFX(Integer buttonNumber, String soundURI);
    void setPlayerName(String playerName);
    void setDifficulty(String difficulty);
    void startGame();
    void closeGame();
}
