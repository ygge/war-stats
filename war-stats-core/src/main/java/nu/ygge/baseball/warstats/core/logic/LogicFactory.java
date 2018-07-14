package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.logic.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.logic.WARYearCalculator;
import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.parser.DataParser;

import java.io.InputStream;

public final class LogicFactory {

    private LogicFactory() {
    }

    public static PlayerYearFinder createFinderWithStats(InputStream... streams) {
        PlayerYearDataCollection playerYearDataCollection = DataParser.parse(streams);
        return new PlayerYearFinder(playerYearDataCollection);
    }

    public static WARYearCalculator createCalculatorWithStats(InputStream... streams) {
        PlayerYearDataCollection playerYearDataCollection = DataParser.parse(streams);
        return new WARYearCalculator(playerYearDataCollection);
    }
}
