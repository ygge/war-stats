package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.api.model.WAR;
import nu.ygge.baseball.warstats.core.api.model.WARAge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class WARWARAgeTypeCalculator extends WARAgeTypeCalculator {

    @Override
    public WARAge add(WARAge age1, WARAge age2) {
        return new WARAge(age1.age, age1.war.add(age2.war), BigDecimal.ZERO);
    }

    @Override
    public List<WARAge> getTotal() {
        Collection<WARAge> warAgeCollection = getWarAges();
        WAR totalWar = warAgeCollection.stream()
                .map(warAge -> warAge.war)
                .reduce(WAR.ZERO, WAR::add);
        BigDecimal totalWarDecimal = new BigDecimal(totalWar.intValue());
        return warAgeCollection.stream()
                .map(warAge -> warPerAge(warAge, totalWarDecimal))
                .collect(Collectors.toList());
    }

    private WARAge warPerAge(WARAge warAge, BigDecimal totalWarDecimal) {
        return new WARAge(warAge.age, warAge.war, new BigDecimal(warAge.war.intValue()).multiply(new BigDecimal(100)).divide(totalWarDecimal, 10, RoundingMode.HALF_UP));
    }
}
