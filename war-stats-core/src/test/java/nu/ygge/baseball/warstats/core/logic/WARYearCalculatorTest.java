package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.testhelp.DataParserTestHelp;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;

public class WARYearCalculatorTest {

    private static WARYearCalculator calculator;

    @BeforeClass
    public static void setupClass() {
        calculator = new WARYearCalculator(DataParserTestHelp.loadTestData());
    }

    @Test
    public void givenStandardTestDataAndStandardSettingsThenCheckCalculatedData() {
        CalculatorSettings settings = CalculatorSettings.Builder.createBuilderWithDefaultValues()
                .build();
        Collection<PlayerWARData> playerWARData = calculator.calculate(settings);

        System.out.println(playerWARData.size());
    }
}
