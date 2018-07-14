package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.model.Player;
import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.Collection;
import java.util.List;

public class FindPlayerCommand extends Command {

    @Override
    public String command() {
        return "find-player";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        String playerName = String.join(" ", parameters);
        Collection<Player> players = environment.getFinder().searchPlayerByName(playerName);
        if (players.isEmpty()) {
            println("No players found");
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Found players:").append(Printer.NEW_LINE);
            appendLeftAlignedStringWithLength(message, "Name", 20);
            appendRightAlignedStringWithLength(message, "ID", 7);
            message.append(Printer.NEW_LINE);
            for (Player player : players) {
                appendLeftAlignedStringWithLength(message, player.name, 20);
                appendRightAlignedStringWithLength(message, player.id.toSimpleString(), 7);
                message.append(Printer.NEW_LINE);
            }
            println(message.toString());
        }
    }

    @Override
    protected String helpString() {
        return "Finds players with names matching the supplied parameters";
    }
}
