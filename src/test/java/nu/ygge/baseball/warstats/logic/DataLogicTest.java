package nu.ygge.baseball.warstats.logic;

import nu.ygge.baseball.warstats.model.Player;
import nu.ygge.baseball.warstats.testhelp.DataParserTestHelp;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class DataLogicTest {

    private static DataLogic logic;

    @BeforeClass
    public static void setupClass() {
        logic = new DataLogic(DataParserTestHelp.loadTestData());
    }

    @Test
    public void givenStandardTestDataWhenSearchingForMikeTroutThenReturnCorrectData() {
        verifySingleSearchName("Mike Trout", new Player(545361, "Mike Trout"));
    }

    @Test
    public void givenStandardTestDataWhenSearchingForMannyMachadoThenReturnCorrectData() {
        verifySingleSearchName("nny mach", new Player(592518, "Manny Machado"));
    }

    @Test
    public void givenStandardTestDataWhenSearchingForStringThatOccursInSeveralPlayersNamesThenFetchAllPlayersMatching() {
        verifyMultipleSearchName("trout", 3);
    }

    private Collection<Player> verifyMultipleSearchName(String searchName, int expectedPlayers) {
        Collection<Player> players = logic.searchPlayerByName(searchName);

        assertEquals(expectedPlayers, players.size());

        return players;
    }

    private void verifySingleSearchName(String searchName, Player expectedPlayers) {
        Collection<Player> players = verifyMultipleSearchName(searchName, 1);
        Player player = players.iterator().next();
        assertEquals(expectedPlayers.id, player.id);
        assertEquals(expectedPlayers.name, player.name);
    }
}
