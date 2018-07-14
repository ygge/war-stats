package nu.ygge.baseball.warstats.terminal.util;

public final class Printer {

    public static final String NEW_LINE = System.lineSeparator();

    private Printer() {
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        if (message.endsWith(NEW_LINE)) {
            print(message);
        } else {
            System.out.println(message);
        }
    }
}
