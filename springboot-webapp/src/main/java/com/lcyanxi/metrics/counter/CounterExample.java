package com.lcyanxi.metrics.counter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 计数器：Counter只是封装了LongAdder，实现了Counting接口，返回数值
 * 【java8 在高并发场景下对 AtomicInteger 的优化 LongAdder】
 * @author lichang
 * @date 2021/3/26
 */
public class CounterExample {
    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1_000);

    public static void main(String[] args) {
        // 1秒输出一次
        reporter.start(10, TimeUnit.SECONDS);

        // 维护一个计数器，可以通过inc()和dec()方法对计数器做修改
        Counter counter = metricRegistry.counter("queue-count", Counter::new);

        new Thread(() -> {
            for (;;) {
                randomSleep();
                queue.add(System.nanoTime());
                counter.inc();
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                randomSleep();
                if (queue.poll() != null) {
                    counter.dec();
                }
            }
        }).start();
    }

    private static void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
