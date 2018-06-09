public class Player{

    private String name;
    private int points;

    public Player(String name){
        this.setName(name);
        this.points = 0;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addPoints(int x){
        this.points = this.points + x;
    }

    public int getPoints(){
        return this.points;
    }

    public String toString(){
        String name = this.getName();
        String points = this.getPoints().toString()

        return name + Default.SEPARATOR + points + "\n";
    }

}
