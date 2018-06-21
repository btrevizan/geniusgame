package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Ranking implements Cloneable, IFileBased{

    private class PointComparator implements Comparator<Player>{

        @Override
        public int compare(Player p1, Player p2){
            return p2.getPoints() - p1.getPoints();
        }
    }

    private ArrayList<Player> rank;
    private final String RANKPATH = "assets/rank.txt";

    public Ranking(){
        this.rank = new ArrayList<Player>();
        this.load();
    }

    /**
     * Based on the points of a player this method decides if the player appears on the rank
     * and if so the player is added to the rank in its rightful place.
     * @param player that has just finished his game
     * @return true if the player gets in the rank list by his points or false if he doesn't.
     */
    public boolean push(Player player){
        int lastIndex = this.getLastIndex();

        if(this.rank.size() >= Default.RANK_SIZE){
            final int playerPoints = player.getPoints();
            final int lastPlayerPoints = this.rank.get(lastIndex).getPoints();

            if (playerPoints < lastPlayerPoints)
                return false;
            else
                this.removeLast();
        } else {
            lastIndex = this.rank.size();
        }

        this.rank.add(lastIndex, player);
        this.sort();
        this.save();

        return true;
    }

    /**
     * Sets the rank.
     * @param rank list of players that have already played and are at the top 10.
     */
    public void setRank(ArrayList<Player> rank){
        this.rank = (ArrayList<Player>)rank;
    }

    /**
     * Get rank.
     * @return a copy of rank
     */
    public ArrayList<Player> getRank(){
        return new ArrayList<Player>(this.rank);
    }

    /**
     * Gets size of the list of players that compose the rank.
     * @return number of players at the rank list.
     */
    private int getLastIndex(){
        return this.rank.size() - 1;
    }

    /**
     * Removes the last player of the rank.
     */
    private void pop(){
        final int lastIndex = this.getLastIndex();
        this.rank.remove(lastIndex);
    }

    /**
     * Sorts the rank according to the player points.
     */
    private void sort(){
        Collections.sort(this.rank, new PointComparator());
    }

    /**
     * Loads a new file to save rank list.
     */
    public void load(){
        AssetFile file = new AssetFile(this.RANKPATH);
        this.setRank((ArrayList<Player>)file.load(Player.class));
    }

    /**
     * Saves rank on a file.
     */
    public void save(){
        AssetFile file = new AssetFile(this.RANKPATH);
        file.save(this.rank);
    }

    private void removeLast(){
        int lastIndex = this.getLastIndex();
        this.rank.remove(lastIndex);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
