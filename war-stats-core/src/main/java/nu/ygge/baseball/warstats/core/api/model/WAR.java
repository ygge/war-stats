package nu.ygge.baseball.warstats.core.api.model;

import nu.ygge.baseball.warstats.core.util.Util;

public final class WAR implements Comparable<WAR> {

    public static final WAR ZERO = new WAR(0);

    private static final String NULL = "NULL";

    private final Integer war100;

    private WAR(Integer war) {
        this.war100 = war;
    }

    static WAR create(String warString) {
        return new WAR(parseWAR(warString));
    }

    public WAR add(WAR war) {
        return new WAR(Util.add(war100, war.war100));
    }

    public String toSimpleString() {
        if (war100 == null) {
            return null;
        }
        return String.format("%.2f", war100/100.0);
    }

    public boolean hasValue() {
        return war100 != null;
    }

    /**
     * Not to be used for display, only meant to be able to compare different values
     */
    public int intValue() {
        return war100;
    }

    private static Integer parseWAR(String warString) {
        if (NULL.equalsIgnoreCase(warString)) {
            return null;
        }
        return parseIntWar(warString);
    }

    private static int parseIntWar(String warString) {
        String wholeNumber, decimalNumber = "0";
        int index = warString.indexOf('.');
        if (index == -1) {
            wholeNumber = warString;
        } else {
            wholeNumber = warString.substring(0, index);
            decimalNumber = warString.substring(index+1);
        }
        return Util.parseInt(wholeNumber)*100 + Util.parseInt(decimalNumber);
    }

    @Override
    public String toString() {
        return String.format("WAR(%s)", toSimpleString());
    }

    @Override
    public int compareTo(WAR other) {
        return other.war100-war100;
    }
}
