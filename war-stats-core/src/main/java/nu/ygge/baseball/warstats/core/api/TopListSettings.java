package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.Interval;

public final class TopListSettings {

    final Interval positionSelection, ageInterval;

    private TopListSettings(Interval positionSelection, Interval ageInterval) {
        this.positionSelection = positionSelection;
        this.ageInterval = ageInterval;
    }

    @SuppressWarnings({"UnusedReturnValue", "unused"})
    public static class Builder {

        private Interval positionSelection, ageInterval;

        private Builder(Interval positionSelection, Interval ageInterval) {
            this.positionSelection = positionSelection;
            this.ageInterval = ageInterval;
        }

        public static TopListSettings.Builder createBuilderWithDefaultValues() {
            return new TopListSettings.Builder(
                    Interval.createWithMax(20),
                    Interval.createWithNoLimits()
            );
        }

        public TopListSettings.Builder setPositionSelection(Interval positionSelection) {
            verifyNotNull(positionSelection);
            this.positionSelection = positionSelection;
            return this;
        }

        public TopListSettings.Builder setAgeInterval(Interval ageInterval) {
            verifyNotNull(ageInterval);
            this.ageInterval = ageInterval;
            return this;
        }

        public TopListSettings build() {
            return new TopListSettings(positionSelection, ageInterval);
        }

        private void verifyNotNull(Interval interval) {
            if (interval == null) {
                throw new IllegalArgumentException("Interval can not be null");
            }
        }
    }
}
