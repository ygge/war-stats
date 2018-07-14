package nu.ygge.baseball.warstats.core.model;

import nu.ygge.baseball.warstats.core.util.Util;

import java.util.Objects;
import java.util.Optional;

public final class PlayerId {

    private final int id;

    private PlayerId(int id) {
        this.id = id;
    }

    public static Optional<PlayerId> create(String idString) {
        Integer intId = Util.parseIntegerSafe(idString);
        return intId == null ? Optional.empty() : Optional.of(new PlayerId(intId));
    }

    @Override
    public String toString() {
        return String.format("ID(%d)", id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerId playerId = (PlayerId) o;
        return id == playerId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
