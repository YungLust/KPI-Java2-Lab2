package org.example;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StatisticsCollector implements Collector<Undead, StatisticsData, StatisticsData> {

    @Override
    public Supplier<StatisticsData> supplier() {
        return StatisticsData::new;
    }

    @Override
    public BiConsumer<StatisticsData, Undead> accumulator() {
        return (stats, undead) -> stats.accept(undead.getAttackDamage());
    }

    @Override
    public BinaryOperator<StatisticsData> combiner() {
        return StatisticsData::combine;
    }

    @Override
    public Function<StatisticsData, StatisticsData> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }
}