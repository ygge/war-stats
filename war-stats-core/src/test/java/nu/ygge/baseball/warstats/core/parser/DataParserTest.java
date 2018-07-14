package nu.ygge.baseball.warstats.core.parser;

import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.testhelp.DataParserTestHelp;
import org.junit.Test;

public class DataParserTest {

    @Test
    public void givenTestDataThenParseCorrectly() {
        PlayerYearDataCollection playerYearData = DataParserTestHelp.loadTestData();

        System.out.println(playerYearData.toCollection().size());
    }
}
