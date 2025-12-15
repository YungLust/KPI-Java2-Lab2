package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class UndeadGatherer {

    public static Collector<Undead, GathererState, List<Undead>>
    skipTypeAndCollect(String typeToSkip, int toSkip, int targetSize) {
        return new Collector<Undead, GathererState, List<Undead>>() {

            @Override
            public Supplier<GathererState> supplier() {
                return () -> new GathererState(typeToSkip, toSkip, targetSize);
            }


            @Override
            public BiConsumer<GathererState, Undead> accumulator() {
                return (state, undead) -> {
                    if (state.result.size() >= state.targetSize) {
                        return;
                    }

                    if (undead.getType().equals(state.typeToSkip) && state.skippedCount < state.toSkip) {
                        state.skippedCount++;
                    } else {
                        state.result.add(undead);
                    }
                };
            }

            @Override
            public BinaryOperator<GathererState> combiner() {
                return (state1, state2) -> {
                    state1.result.addAll(state2.result);
                    state1.skippedCount += state2.skippedCount;
                    return state1;
                };
            }

            @Override
            public Function<GathererState, List<Undead>> finisher() {
                return state -> state.result;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };
    }

    private static class GathererState {
        String typeToSkip;
        int toSkip;
        int skippedCount;
        int targetSize;
        List<Undead> result;

        GathererState(String typeToSkip, int toSkip, int targetSize) {
            this.typeToSkip = typeToSkip;
            this.toSkip = toSkip;
            this.targetSize = targetSize;
            this.skippedCount = 0;
            this.result = new ArrayList<>();
        }

    }
}