package nu.ygge.baseball.warstats.core.util;

import java.util.Objects;

public final class MutablePair<A, B> {

    public A first;
    public B second;

    public MutablePair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutablePair<?, ?> that = (MutablePair<?, ?>) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
