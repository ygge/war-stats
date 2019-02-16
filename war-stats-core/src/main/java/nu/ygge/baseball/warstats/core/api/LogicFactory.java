package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.parser.DataParser;

import java.io.InputStream;

public final class LogicFactory {

    private LogicFactory() {
    }

    public static PlayerYearFinder createFinderWithStats(InputStream... streams) {
        PlayerYearDataCollection playerYearDataCollection = parseStreams(streams);
        return new PlayerYearFinder(playerYearDataCollection);
    }

    public static WARAgeCalculator createCalculatorWithStats(InputStream... streams) {
        PlayerYearDataCollection playerYearDataCollection = parseStreams(streams);
        return new WARAgeCalculator(playerYearDataCollection);
    }

    public static PlayerYearDataCollection parseStreams(InputStream... streams) {
        return DataParser.parse(streams);
    }

    public static PlayerYearFinder createFinderFromPlayerYearData(PlayerYearDataCollection playerYearDataCollection) {
        return new PlayerYearFinder(playerYearDataCollection);
    }

    public static WARAgeCalculator createCalculatorFromPlayerYearData(PlayerYearDataCollection playerYearDataCollection) {
        return new WARAgeCalculator(playerYearDataCollection);
    }
}
