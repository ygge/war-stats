package nu.ygge.baseball.warstats.core.logic;

import java.util.function.Supplier;

enum WARAgeType {
    WAR(WARWARAgeTypeCalculator::new),
    PERCENT(PercentWARAgeTypeCalculator::new);

    private final Supplier<WARAgeTypeCalculator> calculatorSupplier;

    WARAgeType(Supplier<WARAgeTypeCalculator> calculatorSupplier) {
        this.calculatorSupplier = calculatorSupplier;
    }

    WARAgeTypeCalculator createCalculator() {
        return calculatorSupplier.get();
    }
}
