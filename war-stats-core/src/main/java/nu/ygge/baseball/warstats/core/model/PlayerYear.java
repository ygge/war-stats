package nu.ygge.baseball.warstats.core.model;

import java.util.Objects;

public final class PlayerYear {

    public final PlayerId id;
    public final int year;

    public PlayerYear(PlayerId id, int year) {
        this.id = id;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerYear that = (PlayerYear) o;
        return year == that.year
                && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year);
    }
}
