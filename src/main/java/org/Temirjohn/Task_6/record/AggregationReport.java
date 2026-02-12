package org.Temirjohn.Task_6.record;

public record AggregationReport(long successCount, long failureCount, double averageDurationMillis) {
    @Override
    public String toString() {
        return "AggregationReport{" +
                "successCount=" + successCount +
                ", failureCount=" + failureCount +
                ", averageDurationMillis=" + averageDurationMillis +
                '}';
    }
}
