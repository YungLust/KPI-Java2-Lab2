// OutlierAnalysis.java - Клас для аналізу викидів
package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutlierAnalysis {

    public static Map<String, Long> analyzeOutliers(List<Undead> undeads) {
        List<Integer> damages = undeads.stream()
                .map(Undead::getAttackDamage)
                .sorted()
                .toList();

        if (damages.isEmpty()) {
            return Map.of("data", 0L, "outliers", 0L);
        }

        // Обчислення квартилів
        double q1 = getPercentile(damages, 25);
        double q3 = getPercentile(damages, 75);
        double iqr = q3 - q1; // Міжквартильний розмах

        // Межі для викидів
        double lowerBound = q1 - 1.5 * iqr;
        double upperBound = q3 + 1.5 * iqr;

        System.out.printf("Міжквартильний розмах (IQR): %.2f%n", iqr);
        System.out.printf("Q1: %.2f, Q3: %.2f%n", q1, q3);
        System.out.printf("Межі викидів: [%.2f, %.2f]%n", lowerBound, upperBound);

        // Групування за належністю до викидів
        return undeads.stream()
                .collect(Collectors.groupingBy(
                        undead -> {
                            int damage = undead.getAttackDamage();
                            return (damage < lowerBound || damage > upperBound) ? "outliers" : "data";
                        },
                        Collectors.counting()
                ));
    }

    static double getPercentile(List<Integer> sortedList, double percentile) {
        if (sortedList.isEmpty()) return 0;

        double index = (percentile / 100.0) * (sortedList.size() - 1);
        int lowerIndex = (int) Math.floor(index);
        int upperIndex = (int) Math.ceil(index);

        if (lowerIndex == upperIndex) {
            return sortedList.get(lowerIndex);
        }

        double lowerValue = sortedList.get(lowerIndex);
        double upperValue = sortedList.get(upperIndex);
        double fraction = index - lowerIndex;

        return lowerValue + (upperValue - lowerValue) * fraction;
    }
}