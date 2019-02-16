package nu.ygge.baseball.warstats.core.util;

public final class Interval {
    private final Integer min, max;

    private Interval(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public static Interval createWithNoLimits() {
        return new Interval(null, null);
    }

    public static Interval createWithMin(int min) {
        return new Interval(min, null);
    }

    public static Interval createWithMax(int max) {
        return new Interval(null, max);
    }

    public static Interval createWithMinAndMax(int min, int max) {
        return new Interval(min, max);
    }

    public boolean matches(Integer value) {
        if (min == null && max == null) {
            return true;
        }
        if (value == null) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (min != null && value < min) {
            return false;
        }
        return max == null || value <= max;
    }
}
