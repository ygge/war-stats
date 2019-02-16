package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.util.Interval;

public final class CalculatorSettings {

    private final Interval year, plateAppearances, inningsPitched;

    private CalculatorSettings(Interval year, Interval plateAppearances, Interval inningsPitched) {
        this.year = year;
        this.plateAppearances = plateAppearances;
        this.inningsPitched = inningsPitched;
    }

    Interval getYear() {
        return year;
    }

    Interval getPlateAppearances() {
        return plateAppearances;
    }

    Interval getInningsPitched() {
        return inningsPitched;
    }

    public static class Builder {

        private Interval year, plateAppearances, inningsPitched;

        private Builder(Interval year, Interval plateAppearances, Interval inningsPitched) {
            this.year = year;
            this.plateAppearances = plateAppearances;
            this.inningsPitched = inningsPitched;
        }

        public static Builder createBuilderWithDefaultValues() {
            return new Builder(
                    Interval.createWithMinAndMax(1900, 2017),
                    Interval.createWithMin(400),
                    Interval.createWithMin(50)
            );
        }

        public static Builder createBuilderWithNoLimits() {
            return new Builder(
                    Interval.createWithNoLimits(),
                    Interval.createWithNoLimits(),
                    Interval.createWithNoLimits()
            );
        }

        public Builder setYearInterval(Interval year) {
            verifyNotNull(year);
            this.year = year;
            return this;
        }

        public Builder setPlateAppearancesInterval(Interval plateAppearances) {
            verifyNotNull(plateAppearances);
            this.plateAppearances = plateAppearances;
            return this;
        }

        public Builder setInningsPitchedInterval(Interval inningsPitched) {
            verifyNotNull(inningsPitched);
            this.inningsPitched = inningsPitched;
            return this;
        }

        public CalculatorSettings build() {
            return new CalculatorSettings(year, plateAppearances, inningsPitched);
        }

        private void verifyNotNull(Interval interval) {
            if (interval == null) {
                throw new IllegalArgumentException("Interval can not be null");
            }
        }
    }
}
