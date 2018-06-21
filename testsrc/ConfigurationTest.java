package model;

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class ConfigurationTest{

    private Configuration testConfiguration;

    @Before
    public void setUp(){
        this.testConfiguration = new Configuration();
    }

    @Test
    public void testGetSetDifficulty(){
        int difficulty = 10;
        this.testConfiguration.setDifficulty(difficulty);

        assertTrue(this.testConfiguration.getDifficulty().equals(difficulty));
    }

    @Test
    public void testGetSetVolume(){
        double volume = 0.5;

        this.testConfiguration.setVolume(volume);
        assertTrue(this.testConfiguration.getVolume().equals(volume));

        this.testConfiguration.setVolume(-0.1);
        assertTrue(this.testConfiguration.getVolume().equals(volume));

        this.testConfiguration.setVolume(1.1);
        assertTrue(this.testConfiguration.getVolume().equals(volume));
    }

    @Test
    public void testGetColors(){
        ArrayList<SoundedColor> colors = this.testConfiguration.getColors();
        assertTrue(colors.size().equals(4));
    }

    @Test
    public void testMute(){
        this.testConfiguration.mute();
        assertTrue(this.testConfiguration.getVolume().equals(Default.MUTE));
    }

    @Test
    public void testChangeColor(){
        int index = 0;
        String soundURI = '../media/saxophone2.wav';
        SoundedColor color = this.testConfiguration.getColors().get(index);

        this.testConfiguration.changeColor(index, soundURI);
        assertNotSame(color.getSound(), this.testConfiguration.getColors().get(index).getSound());
    }

    @Test
    public void testSave(){
        int colorSize = this.testConfiguration.getColors().size();
        this.testConfiguration.save();

        assertTrue(this.testConfiguration.getColors().size().equals(colorSize));
    }
}
