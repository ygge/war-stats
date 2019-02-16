package nu.ygge.baseball.warstats.core.util;

import nu.ygge.baseball.warstats.core.api.model.Player;
import nu.ygge.baseball.warstats.core.api.model.PlayerTotalWAR;
import nu.ygge.baseball.warstats.core.api.model.PlayerYearData;
import nu.ygge.baseball.warstats.core.api.model.WAR;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public final class PlayerTotalWARCollector implements Collector<PlayerYearData, MutablePair<Player, WAR>, PlayerTotalWAR> {

    @Override
    public Supplier<MutablePair<Player, WAR>> supplier() {
        return () -> new MutablePair<>(null, WAR.ZERO);
    }

    @Override
    public BiConsumer<MutablePair<Player, WAR>, PlayerYearData> accumulator() {
        return (pair, playerData) -> {
            pair.first = playerData.player;
            pair.second = pair.second.add(playerData.war);
        };
    }

    @Override
    public BinaryOperator<MutablePair<Player, WAR>> combiner() {
        return (a, b) -> {
            a.second.add(b.second);
            return a;
        };
    }

    @Override
    public Function<MutablePair<Player, WAR>, PlayerTotalWAR> finisher() {
        return pair -> new PlayerTotalWAR(pair.first, pair.second);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Collector.Characteristics.UNORDERED);
    }
}
