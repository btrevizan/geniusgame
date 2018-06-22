package model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Player implements Cloneable, IInstanceFileBased{

    private StringProperty name;
    private IntegerProperty points;

    public Player(String name){
        this(name, 0);
    }

    public Player(String name, int points){
        this.name = new SimpleStringProperty(name);
        this.points = new SimpleIntegerProperty(points);
    }

    /**
     * Sets player name.
     * @param name string of player name
     */
    public void setName(String name){
        this.name.set(name);
    }

    /**
     * Gets player name.
     * @return string of player name
     */
    public String getName(){
        return this.name.get();
    }

    /**
     * Gets player name property.
     * @return property of player name
     */
    public StringProperty getNameProperty(){
        return this.name;
    }

    /**
     * Add points to the player.
     * @param x number of points to be added to the player.
     */
    public void addPoints(int x){
        int points = this.getPoints();
        this.setPoints(points + x);
    }

    private void setPoints(int points){
        this.points.set(points);
    }

    /**
     * Gets the number of points to be added to the player.
     * @return numeber of points of player.
     */
    public int getPoints(){
        return this.points.get();
    }

    /**
     * Gets player points property.
     * @return property of player points
     */
    public IntegerProperty getPointsProperty(){
        return this.points;
    }

    /**
     * Creates a new player.
     * @param args arry of strings that contains player name and score
     * @return new player object
     */
    public static Object createInstance(String[] args){
        String name = args[0];
        int points = Integer.parseInt(args[1]);

        return new Player(name, points);
    }

    /**
     * Creates a string that contains the name and the score of a player.
     */
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
