package nu.ygge.baseball.warstats.util;

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
}
