package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerId;
import nu.ygge.baseball.warstats.core.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.testhelp.DataParserTestHelp;
import nu.ygge.baseball.warstats.core.testhelp.PlayerIdTestHelp;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerYearFinderTest_getYearDataForPlayer {

    private static PlayerYearFinder logic;

    @BeforeClass
    public static void setupClass() {
        logic = new PlayerYearFinder(DataParserTestHelp.loadTestData());
    }

    @Test
    public void givenStandardTestDataWhenGettingPlayerDataForMikeTroutThenReturnYearsFrom2011To2018() {
        verifyPlayerYearData(545361, 2011, 2018);
    }

    @Test
    public void givenStandardTestDataWhenGettingPlayerDataForBabeRuthThenReturnYearsFrom2011To2018() {
        verifyPlayerYearData(121578, 1914, 1935);
    }

    private void verifyPlayerYearData(int id, int expectedStartYear, int expectedEndYear) {
        PlayerId playerId = PlayerIdTestHelp.createPlayerId(id);
        Collection<PlayerYearData> playerYearsData = logic.getYearDataForPlayer(playerId);
        int expectedNumberOfYears = expectedEndYear - expectedStartYear + 1;
        assertEquals(expectedNumberOfYears, playerYearsData.size());

        Set<Integer> yearsSeen = new HashSet<>();
        for (PlayerYearData playerYear : playerYearsData) {
            assertEquals(playerId, playerYear.id);
            yearsSeen.add(playerYear.year);
        }
        assertEquals(expectedNumberOfYears, yearsSeen.size());
        for (int year = expectedStartYear; year <= expectedEndYear; ++year) {
            assertTrue(yearsSeen.contains(year));
        }
    }
}
