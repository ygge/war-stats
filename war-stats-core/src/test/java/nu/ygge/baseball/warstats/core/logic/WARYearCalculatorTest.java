package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.WARAge;
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
    public void givenStandardTestDataAndStandardSettingsThenCheckCalculatedWARData() {
        CalculatorSettings settings = CalculatorSettings.Builder.createBuilderWithDefaultValues()
                .build();
        Collection<WARAge> warAges = calculator.calculateWARPerAge(settings);

        System.out.println(warAges);
    }

    @Test
    public void givenStandardTestDataAndStandardSettingsThenCheckCalculatedPercentageData() {
        CalculatorSettings settings = CalculatorSettings.Builder.createBuilderWithDefaultValues()
                .build();
        Collection<WARAge> warAges = calculator.calculateWARPercentagePerAge(settings);

        System.out.println(warAges);
    }
}
