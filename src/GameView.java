import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameView extends Application {

    private Stage window;
    private IGameListener listener;
    private Scene menu, game, ranking, askPlayerInfo;
    private Button playButton;
    private Button gameButton, rankingButton, exitButton;
    static final int NUMBER_OF_BUTTONS = 4;

    private ColoredButton[] coloredButtons;
    private int difficulty;
    private String playerName;
    private double volume;

    public GameView(){
        this.coloredButtons = new ColoredButton[NUMBER_OF_BUTTONS];
    }

    private int getDifficulty(){ return this.difficulty; }

    public void setDifficulty(int dif){ this.difficulty = dif; }

    public ColoredButton getColoredButton(int i) { return coloredButtons[i]; }

    public static void main(String[] args){ launch(args); }


    @Override
    public void start(Stage primaryStage) {

        this.window = primaryStage;
        final int WIDTH = 800;
        final int HEIGHT = 800;

        window.setTitle("Genius Game");

        gameButton = new Button("New Game");
        gameButton.setOnAction(e -> window.setScene(askPlayerInfo));

        rankingButton = new Button("Ranking");

        exitButton = new Button("Exit");
        exitButton.setOnAction(e -> this.closeProgram());
        window.setOnCloseRequest(e -> {
            e.consume(); // hey java, we got it form here!
            this.closeProgram();
        });


        TextField nameInput = new TextField("Default");
        ChoiceBox<String> dropdown = new ChoiceBox<>();
        dropdown.getItems().addAll("easy", "medium", "hard");
        // default value
        dropdown.setValue("easy");
        playButton = new Button("Play");
        playButton.setOnAction(e -> {
            this.setPlayerData(nameInput.getText(), dropdown.getValue());
            this.listener.startGame();
        });

        coloredButtons[0] = new ColoredButton("red", "http://www.wavlist.com/soundfx/012/owl-4.wav", e -> this.sendClickedButton(0));
        coloredButtons[1] = new ColoredButton("green", "http://www.wavlist.com/soundfx/011/duck-quack5.wav", e -> this.sendClickedButton(1));
        coloredButtons[2] = new ColoredButton("blue", "http://www.wavlist.com/soundfx/003/cow-moo2.wav", e -> this.sendClickedButton(2));
        coloredButtons[3] = new ColoredButton("yellow", "http://www.wavlist.com/soundfx/009/chicken-3.wav", e -> this.sendClickedButton(3));


        VBox menuLayout = new VBox(20);
        menuLayout.getChildren().addAll(gameButton, rankingButton, exitButton);
        menu = new Scene(menuLayout, WIDTH, HEIGHT);

        VBox askPlayerInfoLayout = new VBox(20);
        askPlayerInfoLayout.getChildren().addAll(nameInput, dropdown, playButton);
        askPlayerInfo = new Scene(askPlayerInfoLayout, WIDTH, HEIGHT);

        VBox gameLayout = new VBox(30);
        gameLayout.getChildren().addAll(coloredButtons[0], coloredButtons[1], coloredButtons[2], coloredButtons[3]);
        game = new Scene(gameLayout, WIDTH, HEIGHT);

        window.setScene(menu);

        this.addGameListener(); // links to a Controller, which links to Model which links to this view -> M-V-C

        window.show();

    }

    public void showNewSequence(ArrayList<Integer> sequence, double volume){
        System.out.print("New Sequence: ");
        System.out.println(sequence.toString()); //todo: make coloredButtons blink
        for(Integer i : sequence){
            try {
                Thread.sleep(this.getTimeSpan());
            }catch(java.lang.InterruptedException e){
                System.out.println(e);
            }
            this.showClickedButton(i, volume);
        }
    }

    public void showNewPoints(Integer points){
        System.out.println(points); // refresh point count
    }

    public void gameOver(){
        System.out.println(" Game Over ");
        window.setScene(menu);
    }

    private void addGameListener(){ this.listener = new GameController(this); }

    private void closeProgram() {
        this.listener.closeGame();
        window.close();
    }

    private void setPlayerData(String playerName, String dif) {
        this.listener.setPlayerName(playerName);
        this.listener.setDifficulty(dif);
        window.setScene(game);
    }

    private int getTimeSpan(){
        return 1000/this.getDifficulty();
    }

    private void sendClickedButton(Integer cb) {
        this.listener.pushAction(cb);
    }

    public void showClickedButton(int buttonIndex, double volume){
        this.coloredButtons[buttonIndex].onClicked(volume);
        //this.coloredButton[buttonIndex].blink();
    }
}