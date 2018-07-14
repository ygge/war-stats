package nu.ygge.baseball.warstats.core.util;

import nu.ygge.baseball.warstats.core.logic.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.parser.DataParser;

import java.io.InputStream;

public final class PlayerYearFinderFactory {

    private PlayerYearFinderFactory() {
    }

    public static PlayerYearFinder createWithStats(InputStream... streams) {
        PlayerYearDataCollection playerYearDataCollection = DataParser.parse(streams);
        return new PlayerYearFinder(playerYearDataCollection);
    }
}
