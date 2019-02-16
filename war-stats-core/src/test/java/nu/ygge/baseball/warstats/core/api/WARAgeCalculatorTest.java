package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.WARAge;
import nu.ygge.baseball.warstats.core.testhelp.DataParserTestHelp;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;

public class WARAgeCalculatorTest {

    private static WARAgeCalculator calculator;

    @BeforeClass
    public static void setupClass() {
        calculator = new WARAgeCalculator(DataParserTestHelp.loadTestData());
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
