package nu.ygge.baseball.warstats.terminal.command;

import nu.ygge.baseball.warstats.core.api.CalculatorSettings;
import nu.ygge.baseball.warstats.core.api.model.WARAge;
import nu.ygge.baseball.warstats.terminal.Environment;
import nu.ygge.baseball.warstats.terminal.util.Printer;

import java.util.Collection;
import java.util.List;

public class AgeWARCommand extends Command {

    @Override
    public String command() {
        return "age-war";
    }

    @Override
    public void perform(Environment environment, List<String> parameters) {
        Collection<WARAge> warAges = environment.getCalculator().calculateWARPerAge(
                CalculatorSettings.Builder.createBuilderWithDefaultValues().build()
        );
        StringBuilder message = new StringBuilder();
        appendRightAlignedStringWithLength(message, "Age", 3);
        appendRightAlignedStringWithLength(message, "WAR", 6);
        appendRightAlignedStringWithLength(message, "%", 6);
        message.append(Printer.NEW_LINE);
        for (WARAge warAge : warAges) {
            appendRightAlignedIntegerWithLength(message, warAge.age, 3);
            appendRightAlignedStringWithLength(message, warAge.war.toSimpleString(), 6);
            appendRightAlignedStringWithLength(message, String.format("%.03f%%", warAge.percentage.doubleValue()), 6);
            message.append(Printer.NEW_LINE);
        }
        println(message.toString());
    }

    @Override
    protected String helpString() {
        return "Lists total WAR per age and in percent";
    }
}
