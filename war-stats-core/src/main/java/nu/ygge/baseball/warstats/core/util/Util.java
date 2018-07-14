package nu.ygge.baseball.warstats.core.util;

public final class Util {

    private Util() {
    }

    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer parseIntegerSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer add(Integer numA, Integer numB) {
        if (numA == null) {
            return numB;
        }
        if (numB == null) {
            return numA;
        }
        return numA + numB;
    }
}
