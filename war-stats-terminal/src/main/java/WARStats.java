import nu.ygge.baseball.warstats.core.logic.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.model.Player;
import nu.ygge.baseball.warstats.core.model.PlayerId;
import nu.ygge.baseball.warstats.core.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.util.PlayerYearFinderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.function.BiFunction;

public class WARStats {

    public static final String COMMAND_QUIT = "quit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_VERBOSE = "verbose";
    public static final String COMMAND_LOAD_DEFAULT_STATS = "load-default-stats";
    public static final String COMMAND_FIND_PLAYER = "find-player";
    public static final String COMMAND_SHOW_PLAYER_STATS = "show-player-stats";

    public static final String NEW_LINE = System.lineSeparator();
    public static final String SPACES = "                                                         ";

    private PlayerYearFinder finder;
    private boolean printErrors = false;

    public static void main(String[] args) {
        new WARStats().run();
    }

    private void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        println("Welcome to WAR-stats, a terminal program for Major League Baseball (MLB) Wins Above Replacement (WAR) statistics");
        while (true) {
            print("> ");
            try {
                String command = in.readLine();
                if (command == null) {
                    continue;
                }
                String trimmedCommand = command.trim();
                if (trimmedCommand.isEmpty()) {
                    continue;
                }
                if (command.equalsIgnoreCase(COMMAND_QUIT)) {
                    break;
                } else if (command.equalsIgnoreCase(COMMAND_LOAD_DEFAULT_STATS)) {
                    loadDefaultStats();
                } else if (command.startsWith(COMMAND_FIND_PLAYER)) {
                    findPlayer(command.substring(COMMAND_FIND_PLAYER.length()+1));
                } else if (command.startsWith(COMMAND_SHOW_PLAYER_STATS)) {
                    showPlayerData(command.substring(COMMAND_SHOW_PLAYER_STATS.length()+1));
                } else if (command.equalsIgnoreCase(COMMAND_VERBOSE)) {
                    updateVerbose();
                } else if (command.equalsIgnoreCase(COMMAND_HELP)) {
                    printHelp();
                } else {
                    println("Command not understood, please use the command 'help' to get info about available commands");
                }
            } catch (IOException e) {
                println("Something went wrong reading from/writing to disk, please retry last command");
                if (printErrors) {
                    e.printStackTrace();
                }
            } catch (RuntimeException e) {
                println("Something went wrong, please retry last command");
                if (printErrors) {
                    e.printStackTrace();
                }
            }
        }
        println("Goodbye, and thank you for using WAR-stats");
    }

    private void updateVerbose() {
        if (printErrors) {
            printErrors = false;
            System.out.println("Errors details will NOT be printed");
        } else {
            printErrors = true;
            System.out.println("Errors details will be printed");
        }
    }

    private void showPlayerData(String playerIdString) {
        PlayerId.create(playerIdString)
                .map(this::showPlayerData)
                .orElseGet(() -> {
                    println(String.format("Id '%s' not supported", playerIdString));
                    return false;
                });
    }

    private boolean showPlayerData(PlayerId playerId) {
        Collection<PlayerYearData> playerYears = finder.getYearDataForPlayer(playerId);
        if (playerYears.isEmpty()) {
            println(String.format("Player with id '%s' not found", playerId.toSimpleString()));
        } else {
            StringBuilder message = new StringBuilder();
            message.append(String.format("Year data for '%s'", playerYears.iterator().next().name)).append(NEW_LINE);
            appendLeftAlignedStringWithLength(message, "Year", 5);
            appendRightAlignedStringWithLength(message, "WAR", 6);
            appendRightAlignedStringWithLength(message, "G", 6);
            appendRightAlignedStringWithLength(message, "PA", 6);
            appendRightAlignedStringWithLength(message, "IP", 6);
            message.append(NEW_LINE);
            for (PlayerYearData playerYear : playerYears) {
                appendLeftAlignedStringWithLength(message, Integer.toString(playerYear.year), 5);
                appendRightAlignedStringWithLength(message, playerYear.war.toSimpleString(), 6);
                appendRightAlignedIntegerWithLength(message, playerYear.games, 6);
                appendRightAlignedIntegerWithLength(message, playerYear.plateAppearances, 6);
                appendRightAlignedIntegerWithLength(message, playerYear.inningsPitched, 6);
                message.append(NEW_LINE);
            }
            println(message.toString());
        }
        return true;
    }

    private void appendLeftAlignedStringWithLength(StringBuilder sb, String str, int length) {
        appendAlignedStringWithLength(sb, str, length, Alignment.LEFT);
    }

    private void appendRightAlignedStringWithLength(StringBuilder sb, String str, int length) {
        appendAlignedStringWithLength(sb, str, length, Alignment.RIGHT);
    }

    private void appendRightAlignedIntegerWithLength(StringBuilder sb, Integer value, int length) {
        String str = Integer.toString(value == null ? 0 : value);
        appendAlignedStringWithLength(sb, str, length, Alignment.RIGHT);
    }

    private void appendAlignedStringWithLength(StringBuilder sb, String str, int length, Alignment alignment) {
        String formatted = str;
        if (str.length() > length) {
            formatted = str.substring(0, length);
        } else if (str.length() < length) {
            formatted = alignment.perform.apply(str, SPACES.substring(0, length-str.length()));
        }
        sb.append(formatted);
    }

    private void findPlayer(String playerName) {
        Collection<Player> players = finder.searchPlayerByName(playerName);
        if (players.isEmpty()) {
            println("No players found");
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Found players:").append(NEW_LINE);
            appendLeftAlignedStringWithLength(message, "Name", 20);
            appendRightAlignedStringWithLength(message, "ID", 7);
            message.append(NEW_LINE);
            for (Player player : players) {
                appendLeftAlignedStringWithLength(message, player.name, 20);
                appendRightAlignedStringWithLength(message, player.id.toSimpleString(), 7);
                message.append(NEW_LINE);
            }
            println(message.toString());
        }
    }

    private void loadDefaultStats() {
        finder = PlayerYearFinderFactory.createWithStats(
                getClass().getResourceAsStream("/war-daily-bat.txt"),
                getClass().getResourceAsStream("/war-daily-pitching.txt")
        );
    }

    private void printHelp() {
        String commandSeparator = ": ";
        StringBuilder message = new StringBuilder();
        message.append("Available commands:").append(NEW_LINE);
        message.append(COMMAND_LOAD_DEFAULT_STATS).append(commandSeparator).append("Loads default stats, updated to 2018-07-12").append(NEW_LINE);
        message.append(COMMAND_HELP).append(commandSeparator).append("Prints this message").append(NEW_LINE);
        message.append(COMMAND_QUIT).append(commandSeparator).append("Exits the program").append(NEW_LINE);
        print(message.toString());
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void println(String message) {
        if (message.endsWith(NEW_LINE)) {
            print(message);
        } else {
            System.out.println(message);
        }
    }

    private enum Alignment {
        LEFT((text, spaces) -> text+spaces),
        RIGHT((text, spaces) -> spaces+text);

        private final BiFunction<String, String, String> perform;

        Alignment(BiFunction<String, String, String> perform) {
            this.perform = perform;;
        }
    }
}
