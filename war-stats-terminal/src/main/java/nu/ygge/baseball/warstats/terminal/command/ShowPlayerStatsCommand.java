package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.api.model.PlayerId;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearData;
import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.Collection;
import java.util.List;

public class ShowPlayerStatsCommand extends Command {

    @Override
    public String command() {
        return "show-player-stats";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        String playerIdString = String.join(" ", parameters);
        PlayerId.create(playerIdString)
                .map(playerId -> showPlayerData(environment, playerId))
                .orElseGet(() -> {
                    println(String.format("Id '%s' not supported", playerIdString));
                    return false;
                });
    }

    @Override
    protected String helpString() {
        return "Lists player stats by year";
    }

    private boolean showPlayerData(Environment environment, PlayerId playerId) {
        Collection<PlayerYearData> playerYears = environment.getFinder().getYearDataForPlayer(playerId);
        if (playerYears.isEmpty()) {
            println(String.format("Player with id '%s' not found", playerId.toSimpleString()));
        } else {
            StringBuilder message = new StringBuilder();
            message.append(String.format("Year data for '%s'", playerYears.iterator().next().name)).append(Printer.NEW_LINE);
            appendLeftAlignedStringWithLength(message, "Year", 5);
            appendRightAlignedStringWithLength(message, "WAR", 6);
            appendRightAlignedStringWithLength(message, "G", 6);
            appendRightAlignedStringWithLength(message, "PA", 6);
            appendRightAlignedStringWithLength(message, "IP", 6);
            message.append(Printer.NEW_LINE);
            for (PlayerYearData playerYear : playerYears) {
                appendLeftAlignedStringWithLength(message, Integer.toString(playerYear.year), 5);
                appendRightAlignedStringWithLength(message, playerYear.war.toSimpleString(), 6);
                appendRightAlignedIntegerWithLength(message, playerYear.games, 6);
                appendRightAlignedIntegerWithLength(message, playerYear.plateAppearances, 6);
                appendRightAlignedIntegerWithLength(message, playerYear.inningsPitched, 6);
                message.append(Printer.NEW_LINE);
            }
            println(message.toString());
        }
        return true;
    }
}
