package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.Player;
import nu.ygge.baseball.warstats.core.api.model.PlayerTotalWAR;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.core.api.model.WARAge;
import nu.ygge.baseball.warstats.core.logic.PlayerWARData;
import nu.ygge.baseball.warstats.core.logic.PlayerWARHelper;
import nu.ygge.baseball.warstats.core.logic.WARAgeType;
import nu.ygge.baseball.warstats.core.logic.WARAgeTypeCalculator;
import nu.ygge.baseball.warstats.core.util.PlayerTotalWARCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class WARAgeCalculator {

    private final PlayerYearDataCollection playerYearDataCollection;

    WARAgeCalculator(PlayerYearDataCollection playerYearDataCollection) {
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

    public Collection<PlayerTotalWAR> calculateTopList(CalculatorSettings calculatorSettings,
                                                       TopListSettings topListSettings) {
        List<PlayerYearData> matchingYearDatas = filterMatchingYearDatas(calculatorSettings);
        Map<Player, PlayerTotalWAR> warByPlayer = matchingYearDatas.stream()
                .collect(Collectors.groupingBy(data -> data.player, new PlayerTotalWARCollector()));
        List<PlayerTotalWAR> playerTotalWar = new ArrayList<>(warByPlayer.values());
        playerTotalWar.sort(Comparator.comparing(war -> war.totalWAR));
        return topListSettings.positionSelection.trimList(playerTotalWar);
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
        if (!settings.year.matches(yearData.year)) {
            return false;
        }
        return settings.plateAppearances.matches(yearData.plateAppearances)
                || settings.inningsPitched.matches(yearData.inningsPitched);
    }
}
