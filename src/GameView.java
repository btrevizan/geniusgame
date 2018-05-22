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
    private String playerName;
    private int difficulty;
    private double volume;

    public GameView(){
        this.volume = 1.0;
        this.coloredButtons = new ColoredButton[NUMBER_OF_BUTTONS];
    }

    private double getVolume(){ return this.volume; }

    public void setVolume(double x){
        if(x > 1.0)
            x = 1.0;
        else if(x < 0)
            x = 0;

        this.volume = x;
    }

    public static void main(String[] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) {

        this.window = primaryStage;
        final int WIDTH = 800;
        final int HEIGHT = 800;

        String playerName;
        String difficulty;

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
            this.getChoice(nameInput.getText(), dropdown.getValue());
            this.newGame();
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
        window.show();

    }

    private void newGame(){ this.addGameListener(); }

    public void showNewSequence(ArrayList<Integer> sequence){
        System.out.print("New Sequence: ");
        System.out.println(sequence.toString()); //todo: make coloredButtons blink
        for(Integer i : sequence){
            try {
                Thread.sleep(this.getTimeSpan());
            }catch(java.lang.InterruptedException e){
                System.out.println(e);
            }
            this.showClickedButton(i);
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
        System.out.println("Call function that check if needs to save player data (top 10)");
        window.close();
    }

    private boolean isValidName(String inputName) { return true; }// inputName.matches("[A-Za-z0-9]+"); }

    private void getChoice(String playerName, String dif) {
        if (this.isValidName(playerName)) {
            this.setPlayerName(playerName);
            this.setDifficulty(dif);
            window.setScene(game);
        } else
            System.out.println("invalid name"); //todo pop up

    }

    public String getPlayerName() { return this.playerName; }

    private void setPlayerName(String name) { this.playerName = name; }

    public int getDifficulty() { return this.difficulty; }

    private void setDifficulty(String dif){
        switch (dif){
            case "easy": this.difficulty = 1;
            break;
            case "medium": this.difficulty = 2;
            break;
            case "hard": this.difficulty = 3;
            break;
            default: this.difficulty = 2;
        }
    }

    private int getTimeSpan(){
        return 1000/this.difficulty;
    }

    private void sendClickedButton(Integer cb) {
        this.showClickedButton(cb);
        this.listener.pushAction(cb);
    }

    private void showClickedButton(int buttonIndex){
        this.coloredButtons[buttonIndex].onClicked(this.getVolume());
        //this.coloredButton[buttonIndex].blink();
    }
}