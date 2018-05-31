import java.util.List;

interface IGamePresenter {
    void showNewSequence(List<Integer> sequence);

    void showNewPoints(int points);

    void setDifficulty(int difficulty);

    ColoredButton getColoredButton(Integer buttonNumber);

    void setVolume(double x);

    void setTimeSpan(int ts);

    void showClickedButton(Integer clickedButton);

    void gameOver();
}
