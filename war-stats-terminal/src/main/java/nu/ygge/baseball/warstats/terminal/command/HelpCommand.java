package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.List;

public class HelpCommand extends Command {

    @Override
    public String command() {
        return "help";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        String commandSeparator = ": ";
        StringBuilder message = new StringBuilder();
        message.append("Available commands:").append(Printer.NEW_LINE);
        for (Command command : Environment.getCommands()) {
            message
                    .append(command.command())
                    .append(commandSeparator)
                    .append(command.helpString())
                    .append(Printer.NEW_LINE);
        }
        print(message.toString());
    }

    @Override
    protected String helpString() {
        return "Prints this message";
    }
}
