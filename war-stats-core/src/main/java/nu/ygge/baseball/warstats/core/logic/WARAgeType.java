package nu.ygge.baseball.warstats.core.logic;

import java.util.function.Supplier;

public enum WARAgeType {
    WAR(WARWARAgeTypeCalculator::new),
    PERCENT(PercentWARAgeTypeCalculator::new);

    private final Supplier<WARAgeTypeCalculator> calculatorSupplier;

    WARAgeType(Supplier<WARAgeTypeCalculator> calculatorSupplier) {
        this.calculatorSupplier = calculatorSupplier;
    }

    public WARAgeTypeCalculator createCalculator() {
        return calculatorSupplier.get();
    }
}
