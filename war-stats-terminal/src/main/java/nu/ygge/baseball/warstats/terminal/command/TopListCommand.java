package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.api.CalculatorSettings;
import nu.ygge.baseball.warstats.core.api.TopListSettings;
import nu.ygge.baseball.warstats.core.api.model.Interval;
import nu.ygge.baseball.warstats.core.api.model.PlayerTotalWAR;
import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.Collection;
import java.util.List;

public class TopListCommand extends Command {

    @Override
    public String command() {
        return "top-list";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        TopListSettings.Builder topListBuilder = TopListSettings.Builder.createBuilderWithDefaultValues();
        if (!parameters.isEmpty()) {
            topListBuilder.setAgeInterval(Interval.createWithMax(Integer.parseInt(parameters.get(0))));
        }

        Collection<PlayerTotalWAR> playerTotalWARs = environment.getCalculator().calculateTopList(
                CalculatorSettings.Builder.createBuilderWithDefaultValues().build(),
                topListBuilder.build()
        );
        StringBuilder message = new StringBuilder();
        message.append("Top list:").append(Printer.NEW_LINE);
        appendLeftAlignedStringWithLength(message, "Name", 20);
        appendRightAlignedStringWithLength(message, "ID", 7);
        appendRightAlignedStringWithLength(message, "WAR", 6);
        message.append(Printer.NEW_LINE);
        for (PlayerTotalWAR playerTotalWAR : playerTotalWARs) {
            appendLeftAlignedStringWithLength(message, playerTotalWAR.player.name, 20);
            appendRightAlignedStringWithLength(message, playerTotalWAR.player.id.toSimpleString(), 7);
            appendRightAlignedStringWithLength(message, playerTotalWAR.totalWAR.toSimpleString(), 6);
            message.append(Printer.NEW_LINE);
        }
        println(message.toString());
    }

    @Override
    protected String helpString() {
        return "Gets top 20 players by WAR, possible to set max age as first parameter";
    }
}
