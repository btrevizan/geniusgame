package model;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class AssetFileTest{

    @Test
    public void testGetPath(){
        String path = '../assets/rank.txt';
        AssetFile assetFile = new AssetFile(path);

        assertTrue(path.equals(assetFile.getPath()));
    }

    @Test
    public void testLoad(){
        AssetFile assetFile = new AssetFile(''); // empty path

        // When a file does not exist,
        // the function should return null
        assertNull(assetFile.load(Player.class));

        assetFile = new AssetFile('../assets/sounded_color.txt');

        // When a class does not have createInstance implemented,
        // the function should return an empty list
        assertTrue(assetFile.load(Difficulty.class).size().equals(0));

        // When a class does have createInstance implemented,
        // the function should return an list of cls objects
        assertFalse(assetFile.load(SoundedColor.class).size().equals(0));
    }

    @Test
    public void testSave(){
        AssetFile assetFile = new AssetFile('../assets/file_that_does_not_exist.txt');
        ArrayList<Player> data = new ArrayList<Player>();

        // When a file does not exist,
        // the function should return False
        assertFalse(assetFile.save(data));

        assetFile = new AssetFile('../assets/test_rank.txt');

        data.add(new Player('player1', 100));
        data.add(new Player('player2', 99));
        data.add(new Player('player3', 49));
        data.add(new Player('player4', 30));

        // When a class does not have createInstance implemented,
        // the function should return an empty list
        assertTrue(assetFile.save(data));

        // Assert if real save the data on file
        assertTrue(data.size().equals(assetFile.load(Player.class).size()));
    }

}
