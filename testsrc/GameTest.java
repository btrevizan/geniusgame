package model;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class GameTest{

    private Game testGame;

    @Before
    public void setUp(){
        this.testGame = new Game();
    }

    @Test
    public void testNewMatch(){
        assertNotNull(this.testGame.newMatch('Brandon'));
        assertNotNull(this.testGame.newMatch(''));
    }

    @Test
    public void testGetRanking(){
        assertNotNull(this.testGame.getRanking());
    }

    @Test
    public void testGetColors(){
        assertNotNull(this.testGame.getColors());
    }

    @Test
    public void testSetDifficulty(){
        this.testGame.setDifficultyEasy();
        assertTrue(this.testGame.getDifficulty().equals(Difficulty.EASY));

        this.testGame.setDifficultyMedium();
        assertTrue(this.testGame.getDifficulty().equals(Difficulty.MEDIUM));

        this.testGame.setDifficultyHard();
        assertTrue(this.testGame.getDifficulty().equals(Difficulty.HARD));
    }

}
