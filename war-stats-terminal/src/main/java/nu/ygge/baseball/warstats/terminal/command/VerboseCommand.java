package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.List;

public class VerboseCommand extends Command {

    @Override
    public String command() {
        return "verbose";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        if (environment.isPrintErrors()) {
            environment.setPrintErrors(false);
            Printer.println("Errors details will NOT be printed");
        } else {
            environment.setPrintErrors(true);
            Printer.println("Errors details will be printed");
        }
    }

    @Override
    protected String helpString() {
        return "Enable or disable printing errors, default is disabled";
    }
}
