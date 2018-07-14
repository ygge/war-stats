package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.terminal.Environment;

import java.util.List;

public class QuitCommand extends Command {

    @Override
    public String command() {
        return "quit";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        println("Goodbye, and thank you for using WAR-stats");
        System.exit(0);
    }

    @Override
    protected String helpString() {
        return "Exits the program";
    }
}
