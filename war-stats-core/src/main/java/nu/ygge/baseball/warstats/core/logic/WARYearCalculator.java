package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class WARYearCalculator {

    private final PlayerYearDataCollection playerYearDataCollection;

    WARYearCalculator(PlayerYearDataCollection playerYearDataCollection) {
        this.playerYearDataCollection = playerYearDataCollection;
    }

    public Collection<PlayerWARData> calculate(CalculatorSettings settings) {
        List<PlayerYearData> matchingYearDatas = filterMatchingYearDatas(settings);
        return PlayerWARHelper.toPlayerWARData(matchingYearDatas);
    }

    private List<PlayerYearData> filterMatchingYearDatas(CalculatorSettings settings) {
        return playerYearDataCollection.toCollection().stream()
                .filter(yearData -> matchesFilter(yearData, settings))
                .collect(Collectors.toList());
    }

    private boolean matchesFilter(PlayerYearData yearData, CalculatorSettings settings) {
        //noinspection SimplifiableIfStatement
        if (!settings.getYear().matches(yearData.year)) {
            return false;
        }
        return settings.getPlateAppearances().matches(yearData.plateAppearances)
                || settings.getInningsPitched().matches(yearData.inningsPitched);
    }
}
