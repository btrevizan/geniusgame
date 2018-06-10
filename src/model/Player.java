public class Player implements Cloneable{

    private String name;
    private int points;

    public Player(String name){
        this.setName(name);
        this.points = 0;
    }

    public Player(String name, int points){
        this.setName(name);
        this.points = points;
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
        String points = Integer.toString(this.getPoints());

        return name + Default.SEPARATOR + points + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
