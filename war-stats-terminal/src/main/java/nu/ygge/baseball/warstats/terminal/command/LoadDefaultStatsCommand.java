package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.logic.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.util.LogicFactory;
import nu.ygge.baseball.warstats.terminal.Environment;

import java.util.List;

public class LoadDefaultStatsCommand extends Command {

    @Override
    public String command() {
        return "load-default-stats";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        PlayerYearFinder finder = LogicFactory.createFinderWithStats(
                getClass().getResourceAsStream("/war-daily-bat.txt"),
                getClass().getResourceAsStream("/war-daily-pitching.txt")
        );
        environment.setFinder(finder);
    }

    @Override
    protected String helpString() {
        return "Loads default stats, updated to 2018-07-12";
    }
}
