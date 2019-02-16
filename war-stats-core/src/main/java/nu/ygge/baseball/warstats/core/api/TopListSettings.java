package nu.ygge.baseball.warstats.core.api;

import nu.ygge.baseball.warstats.core.api.model.Interval;

public final class TopListSettings {

    final Interval positionSelection;

    private TopListSettings(Interval positionSelection) {
        this.positionSelection = positionSelection;
    }

    public static class Builder {

        private Interval positionSelection;

        private Builder(Interval positionSelection) {
            this.positionSelection = positionSelection;
        }

        public static TopListSettings.Builder createBuilderWithDefaultValues() {
            return new TopListSettings.Builder(
                    Interval.createWithMax(20)
            );
        }

        public TopListSettings.Builder setPositionSelection(Interval positionSelection) {
            verifyNotNull(positionSelection);
            this.positionSelection = positionSelection;
            return this;
        }

        public TopListSettings build() {
            return new TopListSettings(positionSelection);
        }

        private void verifyNotNull(Interval interval) {
            if (interval == null) {
                throw new IllegalArgumentException("Interval can not be null");
            }
        }
    }
}
