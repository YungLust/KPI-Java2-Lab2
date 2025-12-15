package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        UndeadGenerator generator = new UndeadGenerator();

        // Параметр N - кількість елементів певного типу для пропуску
        int N = 5;
        String typeToSkip = "Zombie";

        // Створення нескінченного потоку
        Stream<Undead> infiniteStream = Stream.generate(generator::generateOne);

        System.out.println("=== Завдання 2: Генерування 500 свторінь пропускаючи " + N + " елементів типу '" + typeToSkip + "' ===");
        // Збір 500 елементів з пропуском перших N елементів певного типу
        List<Undead> collectedList = infiniteStream
                .limit(10000) // обмежуємо для безпеки
                .collect(UndeadGatherer.skipTypeAndCollect(typeToSkip, N, 500));

        System.out.println("Розмір списку з створіннями: " + collectedList.size());
        collectedList.forEach(System.out::println);

        System.out.println("\n=== Завдання 3: Фільтрація та групування ===");
        // Фільтрація за діапазоном років існування (нп. від 100 до 2000) та групування за типом
        int minYears = 100;
        int maxYears = 2000;

        Map<String, List<Undead>> groupedByType = collectedList.stream()
                .filter(u -> {
                    int years = u.getExistenceYears();
                    return years >= minYears && years <= maxYears;
                })
                .collect(Collectors.groupingBy(Undead::getType));

        System.out.println("Групування за типом (фільтр: " + minYears + "-" + maxYears + " років):");
        groupedByType.forEach((type, list) ->
                System.out.println(type + ": " + list.size() + " елементів"));

        List<Undead> filteredList = groupedByType.values().stream()
                .flatMap(List::stream)
                .toList();

        System.out.println("\n=== Завдання 4: Аналіз сили атаки ===");
        // Збір статистики за силою атаки (Поле Г)
        StatisticsData statistics = filteredList.stream()
                .collect(new StatisticsCollector());

        System.out.println("Результат аналізу:");
        System.out.printf(" - Мінімальна сила атаки: %d%n", statistics.getMin());
        System.out.printf(" - Максимальна сила атаки: %d%n", statistics.getMax());
        System.out.printf(" - Середня сила атаки: %.2f%n", statistics.getAverage());
        System.out.printf(" - Стандартне відхилення: %.2f%n", statistics.getStandardDeviation());

        System.out.println("\n=== Завдання 5: Аналіз викидів ===");
        // Аналіз викидів за силою атаки
        Map<String, Long> outlierAnalysis = OutlierAnalysis.analyzeOutliers(filteredList);

        System.out.println("Результат аналізу викидів:");
        System.out.println("{");
        outlierAnalysis.forEach((key, value) ->
                System.out.printf("  \"%s\": %d%n", key, value));
        System.out.println("}");

        // Додатковий аналіз - показати приклади викидів
        List<Integer> damages = filteredList.stream()
                .map(Undead::getAttackDamage)
                .sorted()
                .toList();

        if (!damages.isEmpty()) {
            double q1 = OutlierAnalysis.getPercentile(damages, 25);
            double q3 = OutlierAnalysis.getPercentile(damages, 75);
            double iqr = q3 - q1;
            double lowerBound = q1 - 1.5 * iqr;
            double upperBound = q3 + 1.5 * iqr;

            System.out.println("\nПриклади викидів:");
            filteredList.stream()
                    .filter(u -> {
                        int damage = u.getAttackDamage();
                        return damage < lowerBound || damage > upperBound;
                    })
                    .limit(5)
                    .forEach(u -> System.out.printf("  %s (тип: %s, атака: %d)%n",
                            u.getName(), u.getType(), u.getAttackDamage()));

        }
    }
}