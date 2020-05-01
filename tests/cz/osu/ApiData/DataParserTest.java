package cz.osu.ApiData;

import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataParserTest {

    @Test
    public void getHeatWhenWantedIsHigherThanCurrentTmp() {
        String expected = "ON";
        DataParser dataParser = new DataParser();
        double roomTmp = 12;
        double currentTmp = 10;
        String result = dataParser.getHeat(roomTmp, currentTmp);
        assertEquals(expected, result);

    }
    @Test
    public void getHeatWhenCurrentIsHigherThanWantedTmp() {
        String expected = "OFF";
        DataParser dataParser = new DataParser();
        double roomTmp = 12;
        double currentTmp = 13;
        String result = dataParser.getHeat(roomTmp, currentTmp);
        assertEquals(expected, result);
    }
    @Test
    public void getHeatWhenCurrentIsSameAsWantedTmp() {
        String expected = "OFF";
        DataParser dataParser = new DataParser();
        double roomTmp = 12;
        double currentTmp = 12;
        String result = dataParser.getHeat(roomTmp, currentTmp);
        assertEquals(expected, result);
    }
}