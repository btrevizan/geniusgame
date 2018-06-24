package app.model;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Player implements IInstanceFileBased{

    private StringProperty name;
    private IntegerProperty points;

    public Player(String name){
        this(name, 0);
    }

    public Player(String name, int points){
        this.name = new SimpleStringProperty(name);
        this.points = new SimpleIntegerProperty(points);
    }

    public void setName(String name){
        this.name.set(name);
    }

    public String getName(){
        return this.name.get();
    }

    public StringProperty getNameProperty(){
        return this.name;
    }

    public void addPoints(int x){
        int points = this.getPoints();
        this.setPoints(points + x);
    }

    private void setPoints(int points){
        this.points.set(points);
    }

    public int getPoints(){
        return this.points.get();
    }

    public IntegerProperty getPointsProperty(){
        return this.points;
    }

    public static Object createInstance(String[] args){
        String name = args[0];
        int points = Integer.parseInt(args[1]);

        return new Player(name, points);
    }

    public String toString(){
        String name = this.getName();
        String points = Integer.toString(this.getPoints());

        return name + Default.SEPARATOR + points + "\n";
    }
}
