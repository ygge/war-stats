package nu.ygge.baseball.warstats.logic;

import nu.ygge.baseball.warstats.model.Player;
import nu.ygge.baseball.warstats.model.PlayerId;
import nu.ygge.baseball.warstats.model.PlayerYear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DataLogic {

    private final List<PlayerYear> playerYears;

    public DataLogic(List<PlayerYear> playerYears) {
        this.playerYears = new ArrayList<>(playerYears);
    }

    public Collection<Player> searchPlayerByName(String name) {
        Map<PlayerId, String> playerNamesById = new HashMap<>();
        for (PlayerYear playerYear : playerYears) {
            playerNamesById.put(playerYear.id, playerYear.name);
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
}
