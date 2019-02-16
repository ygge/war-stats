package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.api.model.WAR;
import nu.ygge.baseball.warstats.core.api.model.WARAge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class PercentWARAgeTypeCalculator extends WARAgeTypeCalculator {

    @Override
    public WARAge add(WARAge age1, WARAge age2) {
        return new WARAge(age1.age, WAR.ZERO, age1.percentage.add(age2.percentage));
    }

    @Override
    public List<WARAge> getTotal() {
        Collection<WARAge> warAgeCollection = getWarAges();
        BigDecimal totalPercent = warAgeCollection.stream()
                .map(warAge -> warAge.percentage)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return warAgeCollection.stream()
                .map(warAge -> getWarAge(totalPercent, warAge))
                .collect(Collectors.toList());
    }

    private WARAge getWarAge(BigDecimal totalPercent, WARAge warAge) {
        return new WARAge(warAge.age, WAR.ZERO, warAge.percentage.multiply(new BigDecimal(100)).divide(totalPercent, 10, RoundingMode.HALF_UP));
    }
}
