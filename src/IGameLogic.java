interface IGameLogic {

    void checkNewTry(Integer theTry);

    void addNextElement();

    void clickedFeedback(Integer i);

    void setVolume(double newVolume);

    void setSoundFX(Integer buttonNumber, String soundURI);

    void setPlayerName(String playerName);

    void setDifficulty(String difficulty);

    void startGame();

    void closeGame();
}
