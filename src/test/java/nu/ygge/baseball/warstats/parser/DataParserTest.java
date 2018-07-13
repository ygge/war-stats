package nu.ygge.baseball.warstats.parser;

import nu.ygge.baseball.warstats.model.PlayerYear;
import nu.ygge.baseball.warstats.testhelp.DataParserTestHelp;
import org.junit.Test;

import java.util.List;

public class DataParserTest {

    @Test
    public void givenTestDataThenParseCorrectly() {
        List<PlayerYear> playerYears = DataParserTestHelp.loadTestData();

        System.out.println(playerYears.size());
    }
}
