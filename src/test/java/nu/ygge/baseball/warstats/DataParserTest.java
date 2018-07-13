package nu.ygge.baseball.warstats;

import nu.ygge.baseball.warstats.model.PlayerYear;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class DataParserTest {

    private static final String FILE_NAME_WAR_BAT = "war_daily_bat.txt";
    private static final String FILE_NAME_WAR_PITCH = "war_daily_pitch.txt";

    @Test
    public void givenTestDataThenParseCorrectly() {
        List<PlayerYear> playerYears = DataParser.parse(
                getResource(FILE_NAME_WAR_BAT),
                getResource(FILE_NAME_WAR_PITCH)
        );

        System.out.println(playerYears.size());
    }

    private InputStream getResource(String fileName) {
        return DataParserTest.class.getResourceAsStream("/" + fileName);
    }
}
