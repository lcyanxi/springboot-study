package com.lcyanxi.metrics.timer;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Timer其实是 Histogram 和 Meter 的结合， histogram 某部分代码/调用的耗时， meter统计TPS
 * 比较方便地统计请求的速率和处理时间
 * @author lichang
 * @date 2021/3/26
 */
public class TimerExample {
    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final Timer timer = registry.timer("request", Timer::new);

    public static void main(String[] args) {
        reporter.start(10, TimeUnit.SECONDS);

        while (true) {
            business();
        }
    }

    private static void business() {
        Timer.Context context = timer.time();

        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long stop = context.stop();
            System.out.println("=============" + stop);
        }
    }
}
