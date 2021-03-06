package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.Player;
import nu.ygge.baseball.warstats.core.api.model.PlayerId;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearDataCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PlayerYearFinder {

    private final List<PlayerYearData> playerYearsData;

    PlayerYearFinder(PlayerYearDataCollection playerYearData) {
        this.playerYearsData = new ArrayList<>(playerYearData.toCollection());
    }

    public Collection<Player> searchPlayerByName(String name) {
        Map<PlayerId, String> playerNamesById = new HashMap<>();
        for (PlayerYearData playerYearData : playerYearsData) {
            playerNamesById.put(playerYearData.player.id, playerYearData.player.name);
        }
        String nameToSearch = name.toLowerCase();
        return playerNamesById.entrySet().stream()
                .filter(playerEntry -> playerEntry.getValue().toLowerCase().contains(nameToSearch))
                .map(playerEntry -> new Player(playerEntry.getKey(), playerEntry.getValue()))
                .collect(Collectors.toList());
    }

    public Collection<PlayerYearData> getYearDataForPlayer(PlayerId playerId) {
        return playerYearsData.stream()
                .filter(playerYear -> playerId.equals(playerYear.player.id))
                .sorted(Comparator.comparingInt(o -> o.year))
                .collect(Collectors.toList());
    }
}
