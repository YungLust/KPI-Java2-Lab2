package org.example;

public class StatisticsData {
    private int count;
    private int min;
    private int max;
    private double sum;
    private double sumOfSquares;

    public StatisticsData() {
        this.count = 0;
        this.min = Integer.MAX_VALUE;
        this.max = Integer.MIN_VALUE;
        this.sum = 0;
        this.sumOfSquares = 0;
    }

    public void accept(int value) {
        count++;
        min = Math.min(min, value);
        max = Math.max(max, value);
        sum += value;
        sumOfSquares += value * value;
    }

    public StatisticsData combine(StatisticsData other) {
        this.count += other.count;
        this.min = Math.min(this.min, other.min);
        this.max = Math.max(this.max, other.max);
        this.sum += other.sum;
        this.sumOfSquares += other.sumOfSquares;
        return this;
    }

    public int getMin() {
        return count > 0 ? min : 0;
    }

    public int getMax() {
        return count > 0 ? max : 0;
    }

    public double getAverage() {
        return count > 0 ? sum / count : 0;
    }

    public double getStandardDeviation() {
        if (count == 0) return 0;
        double mean = getAverage();
        double variance = (sumOfSquares / count) - (mean * mean);
        return Math.sqrt(Math.max(0, variance));
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return String.format("Statistics: min=%d, max=%d, avg=%.2f, stdDev=%.2f, count=%d",
                getMin(), getMax(), getAverage(), getStandardDeviation(), count);
    }
}