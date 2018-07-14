package nu.ygge.baseball.warstats.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class PlayerYearDataCollection implements Iterable<PlayerYearData> {

    private final Map<PlayerYear, PlayerYearData> data = new HashMap<>();

    public void add(PlayerYearDataCollection collection) {
        collection.forEach(this::add);
    }

    public boolean add(PlayerYearData playerYearData) {
        PlayerYear playerYear = playerYearData.getPlayerYear();
        PlayerYearData existingPlayerYearData = data.get(playerYear);
        if (existingPlayerYearData == null) {
            data.put(playerYear, playerYearData);
            return true;
        } else if (existingPlayerYearData != playerYearData) {
            data.put(playerYear, existingPlayerYearData.add(playerYearData));
            return true;
        }
        return false;
    }

    public Collection<PlayerYearData> toCollection() {
        return data.values();
    }

    @Override
    public Iterator<PlayerYearData> iterator() {
        return data.values().iterator();
    }
}
