package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.api.model.PlayerId;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.api.model.WARAge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlayerWARData {

    public final PlayerId playerId;
    public final List<PlayerYearData> playerData;
    public final Map<Integer, WARAge> warByAge;

    public PlayerWARData(PlayerId playerId, List<PlayerYearData> playerData) {
        this.playerId = playerId;
        this.playerData = playerData;
        warByAge = calculatePercentWarByAge(playerData);
    }

    private static Map<Integer, WARAge> calculatePercentWarByAge(List<PlayerYearData> playerData) {
        Map<Integer, WARAge> map = new HashMap<>();
        BigDecimal totalWAR = playerData.stream()
                .map(PlayerWARData::toBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        playerData.forEach(yearData -> map.put(yearData.age, createWARAge(yearData, totalWAR)));
        return map;
    }

    private static WARAge createWARAge(PlayerYearData yearData, BigDecimal totalWAR) {
        BigDecimal percent = calcPercent(totalWAR, yearData);
        return new WARAge(yearData.age, yearData.war, percent);
    }

    private static BigDecimal calcPercent(BigDecimal totalWAR, PlayerYearData yearData) {
        if (totalWAR.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return toBigDecimal(yearData).divide(totalWAR, 10, RoundingMode.HALF_UP);
    }

    private static BigDecimal toBigDecimal(PlayerYearData yearData) {
        return new BigDecimal(yearData.war.intValue());
    }
}
