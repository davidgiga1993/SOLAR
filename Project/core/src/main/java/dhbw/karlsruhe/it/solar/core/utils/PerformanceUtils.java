package dhbw.karlsruhe.it.solar.core.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class PerformanceUtils {

    private static final Map<String, MetricList> METRICS = new HashMap<>();

    private PerformanceUtils() {}


    public static void addMeasurement(String key, long begin, long end) {
        if(!METRICS.containsKey(key)) {
            METRICS.put(key, new MetricList(key));
        }
        METRICS.get(key).add(Math.abs(end - begin));
    }

    private static class MetricList {

        private String key;
        private int count = -10000;
        private long[] deltas = new long[1000];

        public MetricList(String key) {
            this.key = key;
        }

        private void add(long delta) {
            count++;
            if(count >= 0 && count < deltas.length) {
                deltas[count] = delta;
            }
            if(count == deltas.length) {
                report();
            }
        }

        private void report() {
            double average = Arrays.stream(deltas)
                    .average()
                    .getAsDouble();

            long min = Arrays.stream(deltas)
                    .min()
                    .getAsLong();

            long max = Arrays.stream(deltas)
                    .max()
                    .getAsLong();

            long sum = Arrays.stream(deltas).sum();

            System.out.println("################################");
            System.out.println("##    Reporting Performance   ##");
            System.out.println("##    " + key);
            System.out.println("################################");
            System.out.println("## MIN " + min);
            System.out.println("## MAX " + max);
            System.out.println("## SUM " + sum);
            System.out.println("## AVG " + average);
        }
    }
}
