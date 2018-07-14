package nu.ygge.baseball.warstats.terminal;

import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class WARStats {

    private final Environment environment;

    private WARStats() {
        environment = new Environment();
    }

    public static void main(String[] args) {
        new WARStats().run();
    }

    private void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Printer.println("Welcome to WAR-stats, a terminal program for Major League Baseball (MLB) Wins Above Replacement (WAR) statistics");
        while (true) {
            Printer.print("> ");
            try {
                String commandString = in.readLine();
                if (commandString == null) {
                    continue;
                }
                String trimmedCommand = commandString.trim();
                if (trimmedCommand.isEmpty()) {
                    continue;
                }
                String[] split = trimmedCommand.split(" ");
                environment.getCommand(split[0])
                        .map(command -> {
                            command.perform(environment, toParameters(split));
                            return true;
                        })
                        .orElseGet(() -> {
                            Printer.println("Command not understood, please use the command 'help' to get info about available commands");
                            return false;
                        });
            } catch (IOException e) {
                Printer.println("Something went wrong reading from/writing to disk, please retry last command");
                if (environment.isPrintErrors()) {
                    e.printStackTrace();
                }
            } catch (RuntimeException e) {
                Printer.println("Something went wrong, please retry last command");
                if (environment.isPrintErrors()) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<String> toParameters(String[] strings) {
        return Arrays.asList(strings).subList(1, strings.length);
    }
}
