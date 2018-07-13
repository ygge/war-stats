package nu.ygge.baseball.warstats.testhelp;

import nu.ygge.baseball.warstats.model.PlayerYear;
import nu.ygge.baseball.warstats.parser.DataParser;
import nu.ygge.baseball.warstats.parser.DataParserTest;

import java.io.InputStream;
import java.util.List;

public class DataParserTestHelp {

    private static final String FILE_NAME_WAR_BAT = "war_daily_bat.txt";
    private static final String FILE_NAME_WAR_PITCH = "war_daily_pitch.txt";

    public static List<PlayerYear> loadTestData() {
        return DataParser.parse(
                getResource(FILE_NAME_WAR_BAT),
                getResource(FILE_NAME_WAR_PITCH)
        );
    }

    private static InputStream getResource(String fileName) {
        return DataParserTestHelp.class.getResourceAsStream("/" + fileName);
    }
}
