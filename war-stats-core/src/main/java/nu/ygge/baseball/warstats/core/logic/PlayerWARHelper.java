package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.PlayerId;
import nu.ygge.baseball.warstats.core.model.PlayerYearData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class PlayerWARHelper {

    private PlayerWARHelper() {
    }

    static Collection<PlayerWARData> toPlayerWARData(List<PlayerYearData> allPlayerData) {
        Map<PlayerId, List<PlayerYearData>> dataByPlayer = groupByPlayer(allPlayerData);
        return dataByPlayer.entrySet().stream()
                .map(entry -> new PlayerWARData(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static Map<PlayerId, List<PlayerYearData>> groupByPlayer(List<PlayerYearData> yearData) {
        Map<PlayerId, List<PlayerYearData>> map = new HashMap<>();
        for (PlayerYearData data : yearData) {
            if (!map.containsKey(data.id)) {
                map.put(data.id, new ArrayList<>());
            }
            map.get(data.id).add(data);
        }
        return map;
    }
}
