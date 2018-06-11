package model;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class Ranking implements Cloneable{

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

    private void load(){
        Path filepath = Paths.get(this.RANKPATH);

        try{
            BufferedReader file = Files.newBufferedReader(filepath, Charset.defaultCharset());

            String line = file.readLine();

            while(line != null){
                String[] info = line.split(Default.SEPARATOR);

                String name = info[0];
                int points = Integer.parseInt(info[1]);

                Player player = new Player(info[0], points);
                this.rank.add(player);

                line = file.readLine();
            }

            file.close();
        } catch(IOException e) {
            System.err.format("Erro de I/O: %s%n", e);
        }
    }

    private void save(){
        try{
            PrintWriter file = new PrintWriter(this.RANKPATH);

            for(Player player: this.rank){
                file.write(player.toString());
            }

            file.close();

        } catch(FileNotFoundException e) {
            System.err.format("FileNotFound: %s%n", e);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
