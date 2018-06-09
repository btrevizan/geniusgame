import java.util.Arryalist;

public class Configuration{

    private double volume;
    private double difficulty;
    private ArrayList<Color> colors;

    public Configuration(){
        this.setDifficulty(Default.DIFFICULTY);
        this.setVolume(Default.VOLUME);
        this.colors = new ArrayList<Color>();
    }

    public double getDifficulty(){
        return this.difficulty;
    }

    public void setDifficulty(double difficulty){
        this.difficulty = difficulty;
    }

    public double getVolume(){
        return this.volume;
    }

    public void setVolume(double volume){
        if(volume < Default.MUTE and volume > Default.VOLUME_HIGH)
            return;

        this.volume = volume;
    }

    public ArrayList<Color> getColors(){
        return this.colors.clone();
    }

    public void mute(){
        this.setVolume(Default.MUTE);
    }

    public boolean addColor(Color color){
        return this.colors.add(color);
    }

    public Color removeColor(int index){
        return this.colors.remove(index);
    }
}
