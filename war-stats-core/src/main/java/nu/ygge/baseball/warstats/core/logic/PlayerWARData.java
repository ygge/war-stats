package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerId;
import nu.ygge.baseball.warstats.core.model.PlayerYearData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class PlayerWARData {

    final PlayerId playerId;
    final List<PlayerYearData> playerData;
    final Map<Integer, BigDecimal> percentWarByYear;

    PlayerWARData(PlayerId playerId, List<PlayerYearData> playerData) {
        this.playerId = playerId;
        this.playerData = playerData;
        percentWarByYear = calculatePercentWarByYear(playerData);
    }

    private static Map<Integer, BigDecimal> calculatePercentWarByYear(List<PlayerYearData> playerData) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        BigDecimal totalWAR = playerData.stream()
                .map(PlayerWARData::toBigDecimal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        playerData.forEach(yearData -> map.put(yearData.year, calcPercent(totalWAR, yearData)));
        return map;
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
