package nu.ygge.baseball.warstats.terminal;

import nu.ygge.baseball.warstats.core.api.PlayerYearFinder;
import nu.ygge.baseball.warstats.core.api.WARAgeCalculator;
import nu.ygge.baseball.warstats.terminal.command.AgeWARCommand;
import nu.ygge.baseball.warstats.terminal.command.Command;
import nu.ygge.baseball.warstats.terminal.command.FindPlayerCommand;
import nu.ygge.baseball.warstats.terminal.command.HelpCommand;
import nu.ygge.baseball.warstats.terminal.command.LoadDefaultStatsCommand;
import nu.ygge.baseball.warstats.terminal.command.PercentWARCommand;
import nu.ygge.baseball.warstats.terminal.command.QuitCommand;
import nu.ygge.baseball.warstats.terminal.command.ShowPlayerStatsCommand;
import nu.ygge.baseball.warstats.terminal.command.TopListCommand;
import nu.ygge.baseball.warstats.terminal.command.VerboseCommand;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Environment {

    private static final List<Command> COMMANDS = Arrays.asList(
            new HelpCommand(),
            new QuitCommand(),
            new VerboseCommand(),

            new AgeWARCommand(),
            new FindPlayerCommand(),
            new LoadDefaultStatsCommand(),
            new PercentWARCommand(),
            new ShowPlayerStatsCommand(),
            new TopListCommand()
    );

    private PlayerYearFinder finder;
    private WARAgeCalculator calculator;
    private boolean printErrors = false;

    public PlayerYearFinder getFinder() {
        return finder;
    }

    public void setFinder(PlayerYearFinder finder) {
        this.finder = finder;
    }

    public boolean isPrintErrors() {
        return printErrors;
    }

    public void setPrintErrors(boolean printErrors) {
        this.printErrors = printErrors;
    }

    public WARAgeCalculator getCalculator() {
        return calculator;
    }

    public void setCalculator(WARAgeCalculator calculator) {
        this.calculator = calculator;
    }

    Optional<Command> getCommand(String commandString) {
        for (Command command : COMMANDS) {
            if (commandString.equalsIgnoreCase(command.command())) {
                return Optional.of(command);
            }
        }
        return Optional.empty();
    }

    public static Collection<Command> getCommands() {
        return Collections.unmodifiableCollection(COMMANDS);
    }
}
