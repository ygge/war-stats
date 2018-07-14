import nu.ygge.baseball.warstats.core.logic.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.model.Player;
import nu.ygge.baseball.warstats.core.util.PlayerYearFinderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class WARStats {

    public static final String COMMAND_QUIT = "quit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_LOAD_DEFAULT_STATS = "load-default-stats";
    public static final String COMMAND_FIND_PLAYER = "find-player";

    public static final String NEW_LINE = System.lineSeparator();

    private PlayerYearFinder finder;

    public static void main(String[] args) {
        new WARStats().run();
    }

    private void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        println("Welcome to WAR-stats, a terminal program for Major League Baseball (MLB) Wins Above Replacement (WAR) statistics");
        while (true) {
            print("> ");
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                println("Could not read response, please try again");
                continue;
            }
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
            } else if (command.equalsIgnoreCase(COMMAND_HELP)) {
                printHelp();
            } else {
                println("Command not understood, please use the command 'help' to get info about available commands");
            }
        }
        println("Goodbye, and thank you for using WAR-stats");
    }

    private void findPlayer(String playerName) {
        String separator = ": ";
        Collection<Player> players = finder.searchPlayerByName(playerName);
        if (players.isEmpty()) {
            println("No players found");
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Found players:").append(NEW_LINE);
            for (Player player : players) {
                message.append(player.name).append(separator).append(player.id.toSimpleString()).append(NEW_LINE);
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
        System.out.println(message);
    }
}
