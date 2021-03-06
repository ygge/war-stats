package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.List;
import java.util.function.BiFunction;

public abstract class Command {

    private static final String SPACES = "                                                         ";

    public abstract String command();

    public abstract void perform(Environment environment, List<String> parameters);

    protected abstract String helpString();

    void print(String message) {
        Printer.print(message);
    }

    void println(String message) {
        Printer.println(message);
    }

    void appendLeftAlignedStringWithLength(StringBuilder sb, String str, int length) {
        appendAlignedStringWithLength(sb, str, length, Alignment.LEFT);
    }

    void appendRightAlignedStringWithLength(StringBuilder sb, String str, int length) {
        appendAlignedStringWithLength(sb, str, length, Alignment.RIGHT);
    }

    void appendRightAlignedIntegerWithLength(StringBuilder sb, Integer value, int length) {
        String str = Integer.toString(value == null ? 0 : value);
        appendAlignedStringWithLength(sb, str, length, Alignment.RIGHT);
    }

    private void appendAlignedStringWithLength(StringBuilder sb, String str, int length, Alignment alignment) {
        String formatted;
        if (str.length() >= length) {
            formatted = alignment.perform.apply(str.substring(0, length-1), SPACES.substring(0, 1));
        } else {
            formatted = alignment.perform.apply(str, SPACES.substring(0, length-str.length()));
        }
        sb.append(formatted);
    }

    protected enum Alignment {
        LEFT((text, spaces) -> text+spaces),
        RIGHT((text, spaces) -> spaces+text);

        private final BiFunction<String, String, String> perform;

        Alignment(BiFunction<String, String, String> perform) {
            this.perform = perform;
        }
    }
}
