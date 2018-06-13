package model;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class Ranking implements Cloneable, IFileBased{

    private class PointComparator implements Comparator<Player>{

        @Override
        public int compare(Player p1, Player p2){
            return p2.getPoints() - p1.getPoints();
        }
    }

    private ArrayList<Player> rank;
    private final String RANKPATH = "../assets/rank.txt";

    public Ranking(){
        this.rank = new ArrayList<Player>();
        this.load();
    }

    public boolean push(Player player){
        int lastIndex = this.getLastIndex();

        if(this.rank.size() == Default.RANK_SIZE){
            final int playerPoints = player.getPoints();
            final int lastPlayerPoints = this.rank.get(lastIndex).getPoints();

            if (playerPoints < lastPlayerPoints)
                return false;
        } else {
            lastIndex = this.rank.size();
        }

        this.rank.add(lastIndex, player);
        this.sort();
        this.save();

        return true;
    }

    public void setRank(ArrayList<Player> rank){
        this.rank = (ArrayList<Player>)rank;
    }

    public Iterator getRank(){
        final Iterator<Player> rank = this.rank.iterator();

        return new Iterator<Player>(){

            public boolean hasNext(){
                return rank.hasNext();
            }

            public Player next(){
                return rank.next();
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    private int getLastIndex(){
        return this.rank.size() - 1;
    }

    private void pop(){
        final int lastIndex = this.getLastIndex();
        this.rank.remove(lastIndex);
    }

    private void sort(){
        Collections.sort(this.rank, new PointComparator());
    }

    public void load(){
        AssetFile file = new AssetFile(this.RANKPATH);
        this.setRank((ArrayList<Player>)file.load(Player.class));
    }

    public void save(){
        AssetFile file = new AssetFile(this.RANKPATH);
        file.save(this.rank);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
