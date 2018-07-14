package nu.ygge.baseball.warstats.parser;

import nu.ygge.baseball.warstats.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.testhelp.DataParserTestHelp;
import org.junit.Test;

public class DataParserTest {

    @Test
    public void givenTestDataThenParseCorrectly() {
        PlayerYearDataCollection playerYearData = DataParserTestHelp.loadTestData();

        System.out.println(playerYearData.toCollection().size());
    }
}
