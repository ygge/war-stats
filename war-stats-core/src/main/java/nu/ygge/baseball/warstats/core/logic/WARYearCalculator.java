package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.model.WARAge;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class WARYearCalculator {

    private final PlayerYearDataCollection playerYearDataCollection;

    WARYearCalculator(PlayerYearDataCollection playerYearDataCollection) {
        this.playerYearDataCollection = playerYearDataCollection;
    }

    public Collection<WARAge> calculateWARPerAge(CalculatorSettings settings) {
        Collection<PlayerWARData> playerWARData = toPlayerWARData(settings);
        return calculateWarAge(playerWARData, WARAgeType.WAR);
    }

    public Collection<WARAge> calculateWARPercentagePerAge(CalculatorSettings settings) {
        Collection<PlayerWARData> playerWARData = toPlayerWARData(settings);
        return calculateWarAge(playerWARData, WARAgeType.PERCENT);
    }

    private Collection<WARAge> calculateWarAge(Collection<PlayerWARData> playerWARData, WARAgeType type) {
        WARAgeTypeCalculator calculator = type.createCalculator();
        playerWARData.forEach(yeardata -> yeardata.warByAge.values().forEach(calculator::add));
        List<WARAge> result = calculator.getTotal();
        result.sort(Comparator.comparingInt(o -> o.age));
        return result;
    }

    private Collection<PlayerWARData> toPlayerWARData(CalculatorSettings settings) {
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
