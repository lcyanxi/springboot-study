package com.lcyanxi.metrics.gauges;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lichang
 * @date 2021/3/25
 */
public class SimpleGauge {
    private static final MetricRegistry registry = new MetricRegistry();
    private static final ConsoleReporter reporter =  ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1_000);


    public static void main(String[] args) {

        registry.register(MetricRegistry.name(SimpleGauge.class,"queue-size"),(Gauge<Integer>) queue::size);
        // 时间间隔
        reporter.start(1,TimeUnit.SECONDS);
        new Thread(()->{
            for (;;){
                randomSleep();
                queue.add(System.nanoTime());
            }
        }).start();

        new Thread(()->{
            for (;;){
                randomSleep();
                if (queue.size() > 0){
                    queue.poll();
                }
            }
        }).start();
    }
    private static void randomSleep()  {
        try {
            TimeUnit.SECONDS.sleep((ThreadLocalRandom.current().nextInt(10)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
