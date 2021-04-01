package com.lcyanxi.metrics.histogram;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lichang
 * @date 2021/3/26
 */
public class ExponentiallyDecayingReservoirsHistogramExample {

    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    // 基于指数级别的抽样算法，根据更新时间与开始时间的差值转化为权重值，权重越大数据被保留的几率越大
    private static final Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());

    public static void main(String[] args) {
        reporter.start(10, TimeUnit.SECONDS);
        registry.register("ExponentiallyDecayingReservoir", histogram);

        while (true) {
            doSearch();
            randomSleep();
        }
    }

    private static void doSearch() {
        histogram.update(ThreadLocalRandom.current().nextInt(10));
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
