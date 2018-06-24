package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Ranking implements IFileBased{

    private class PointComparator implements Comparator<Player>{

        @Override
        public int compare(Player p1, Player p2){
            return p2.getPoints() - p1.getPoints();
        }
    }

    private List<Player> rank;
    private final String RANKPATH = "resources/rank.txt";

    public Ranking(){
        this.rank = new ArrayList<>();
        this.load();
    }

    public boolean push(Player player){
        if(this.rank.size() < Default.RANK_SIZE){
            this.addPlayer(player);
            return true;
        }

        final int playerPoints = player.getPoints();
        final int lastPlayerPoints = this.getLast().getPoints();

        if (playerPoints < lastPlayerPoints)
            return false;

        this.removeLast();
        this.addPlayer(player);
        return true;
    }

    public void setRank(List<Player> rank){
        this.rank = rank;
    }

    public ArrayList<Player> getRank(){
        return new ArrayList<>(this.rank);
    }

    private void addPlayer(Player player){
        this.rank.add(player);
        this.sort();
        this.save();
    }

    private int getLastIndex(){
        if(this.rank.size() == 0)
            return 0;

        return this.rank.size() - 1;
    }

    private Player getLast(){
        final int lastIndex = this.getLastIndex();
        return this.rank.get(lastIndex);
    }

    private void pop(){
        final int lastIndex = this.getLastIndex();
        this.rank.remove(lastIndex);
        this.save();
    }

    private void sort(){
        Collections.sort(this.rank, new PointComparator());
    }

    public void load(){
        AssetFile file = new AssetFile(this.RANKPATH);
        this.setRank((ArrayList<Player>) file.load(Player.class));
    }

    public void save(){
        AssetFile file = new AssetFile(this.RANKPATH);
        file.save(this.rank);
    }

    private void removeLast(){
        int lastIndex = this.getLastIndex();
        this.rank.remove(lastIndex);
    }
}
