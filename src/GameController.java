import java.util.ArrayList;
import java.util.Collections;

public class GameController implements IGameListener{
    private GameView view;
    private GameModel model;
    private ArrayList<Integer> roundTry;

    public GameController(GameView gv){
        this.view = gv;
        this.model = new GameModel(gv.NUMBER_OF_BUTTONS, gv.getDifficulty());
        this.model.newRandomRecord();
        this.updateViewSequence();
        this.newRoundTry();
    }

    void newRoundTry(){
        this.roundTry = new ArrayList<>();
    }

    private void addTry(Integer singleTry){
        this.roundTry.add(singleTry);
        if(this.roundTry.size() == model.getSequenceLength()) {
            this.checkTry();
            this.newRoundTry();
        }else
            this.checkAlreadyFailed();

    }

    private void checkTry(){
        if(model.checkSequence(roundTry)) {

            model.newRandomRecord();

            view.showNewPoints(model.getPoints());
            this.updateViewSequence();
        }else
            this.roundOver();
    }

    private void checkAlreadyFailed(){
        ArrayList<Integer> m = model.getSequence();
        // if the sublist doesn't start at zero, then the player already got the sequence wrong. Game over.
        if(Collections.indexOfSubList(m, this.roundTry) != 0)
            this.roundOver();
    }

    private void roundOver(){
        // todo: save if top 10 (here or a method in model?)
        this.view.gameOver();
        this.roundTry = new ArrayList<>();
    }

    private void updateViewSequence(){
        view.showNewSequence(model.getSequence());
    }

    @Override
    public void pushAction(Integer i) {
        this.addTry(i);
        System.out.println(this.roundTry.toString());
    }
}
