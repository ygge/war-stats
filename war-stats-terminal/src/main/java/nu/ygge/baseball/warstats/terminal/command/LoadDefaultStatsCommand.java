package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.api.LogicFactory;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearDataCollection;
import nu.ygge.baseball.warstats.terminal.Environment;

import java.util.List;

public class LoadDefaultStatsCommand extends Command {

    @Override
    public String command() {
        return "load-default-stats";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        PlayerYearDataCollection collection = LogicFactory.parseStreams(
                getClass().getResourceAsStream("/war-daily-bat.txt"),
                getClass().getResourceAsStream("/war-daily-pitching.txt")
        );
        environment.setFinder(LogicFactory.createFinderFromPlayerYearData(collection));
        environment.setCalculator(LogicFactory.createCalculatorFromPlayerYearData(collection));
    }

    @Override
    protected String helpString() {
        return "Loads default stats, updated to 2018-12-15";
    }
}
