package nu.ygge.baseball.warstats.core.logic;

import nu.ygge.baseball.warstats.core.model.WARAge;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class WARAgeTypeCalculator {

    private final Map<Integer, WARAge> warPerAge = new HashMap<>();

    abstract WARAge add(WARAge age1, WARAge age2);

    abstract List<WARAge> getTotal();

    void add(WARAge warAge) {
        WARAge prevValue = warPerAge.get(warAge.age);
        if (prevValue == null) {
            warPerAge.put(warAge.age, warAge);
        } else {
            warPerAge.put(warAge.age, add(prevValue, warAge));
        }
    }

    protected Collection<WARAge> getWarAges() {
        return warPerAge.values();
    }
}
