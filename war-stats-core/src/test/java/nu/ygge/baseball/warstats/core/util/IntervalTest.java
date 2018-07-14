package nu.ygge.baseball.warstats.core.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntervalTest {

    @Test
    public void givenIntervalWithNoLimitsThenNullShouldSucceed() {
        boolean matches = Interval.createWithNoLimits().matches(null);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithNoLimitsThenAnyNumberShouldSucceed() {
        boolean matches = Interval.createWithNoLimits().matches(Integer.MIN_VALUE);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMinLimitThenNullShouldNotSucceed() {
        boolean matches = Interval.createWithMin(100).matches(null);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMinLimitThenLessValueShouldNotSucceed() {
        boolean matches = Interval.createWithMin(0).matches(-1);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMinLimitThenEqualValueShouldSucceed() {
        boolean matches = Interval.createWithMin(0).matches(0);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMinLimitThenLargerValueShouldSucceed() {
        boolean matches = Interval.createWithMin(0).matches(1);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMaxLimitThenNullShouldNotSucceed() {
        boolean matches = Interval.createWithMax(100).matches(null);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMaxLimitThenLessValueShouldSucceed() {
        boolean matches = Interval.createWithMax(10).matches(-1);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMaxLimitThenEqualValueShouldSucceed() {
        boolean matches = Interval.createWithMax(10).matches(10);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMaxLimitThenLargerValueShouldNotSucceed() {
        boolean matches = Interval.createWithMax(10).matches(20);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenNullShouldNotSucceed() {
        boolean matches = Interval.createWithMinAndMax(100, 200).matches(null);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenLessThanMinShouldNotSucceed() {
        boolean matches = Interval.createWithMinAndMax(100, 200).matches(99);

        assertEquals(false, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenEqualToMinShouldSucceed() {
        boolean matches = Interval.createWithMinAndMax(10, 20).matches(10);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenMiddleValueShouldSucceed() {
        boolean matches = Interval.createWithMinAndMax(10, 20).matches(15);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenEqualToMaxShouldSucceed() {
        boolean matches = Interval.createWithMinAndMax(10, 20).matches(20);

        assertEquals(true, matches);
    }

    @Test
    public void givenIntervalWithMinAndMaxLimitThenGreaterThanMaxShouldNotSucceed() {
        boolean matches = Interval.createWithMinAndMax(10, 20).matches(21);

        assertEquals(false, matches);
    }
}
