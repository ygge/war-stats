package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;

import java.util.List;
import java.util.stream.Collectors;

public final class WARYearCalculator {

    private final PlayerYearDataCollection playerYearDataCollection;

    public WARYearCalculator(PlayerYearDataCollection playerYearDataCollection) {
        this.playerYearDataCollection = playerYearDataCollection;
    }

    public void calculate(CalculatorSettings settings) {
        List<PlayerYearData> matchingYearDatas = filterMatchingYearDatas(settings);
    }

    private List<PlayerYearData> filterMatchingYearDatas(CalculatorSettings settings) {
        return playerYearDataCollection.toCollection().stream()
                .filter(yearData -> matchesFilter(yearData, settings))
                .collect(Collectors.toList());
    }

    private boolean matchesFilter(PlayerYearData settings, CalculatorSettings yearData) {
        return false;
    }
}
