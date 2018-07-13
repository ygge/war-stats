package nu.ygge.baseball.warstats.logic;

import nu.ygge.baseball.warstats.model.Player;
import nu.ygge.baseball.warstats.model.PlayerId;
import nu.ygge.baseball.warstats.model.PlayerYearData;
import nu.ygge.baseball.warstats.model.PlayerYearDataCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DataLogic {

    private final List<PlayerYearData> playerYearsData;

    public DataLogic(PlayerYearDataCollection playerYearData) {
        this.playerYearsData = new ArrayList<>(playerYearData.toCollection());
    }

    public Collection<Player> searchPlayerByName(String name) {
        Map<PlayerId, String> playerNamesById = new HashMap<>();
        for (PlayerYearData playerYearData : playerYearsData) {
            playerNamesById.put(playerYearData.id, playerYearData.name);
        }
        String nameToSearch = name.toLowerCase();
        List<Player> players = new ArrayList<>();
        for (Map.Entry<PlayerId, String> playerEntry : playerNamesById.entrySet()) {
            if (playerEntry.getValue().toLowerCase().contains(nameToSearch)) {
                players.add(new Player(playerEntry.getKey(), playerEntry.getValue()));
            }
        }
        return players;
    }

    public Collection<PlayerYearData> getYearDataForPlayer(PlayerId playerId) {
        return playerYearsData.stream()
                .filter(playerYear -> playerId.equals(playerYear.id))
                .collect(Collectors.toList());
    }
}
