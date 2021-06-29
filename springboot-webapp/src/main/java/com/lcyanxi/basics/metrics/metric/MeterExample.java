package com.lcyanxi.basics.metrics.metric;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Meter度量一系列事件发生的速率(rate)，例如TPS。Meters会统计最近1分钟，5分钟，15分钟，还有全部时间的速率。
 * @author lichang
 * @date 2021/3/25
 */
public class MeterExample {
    // 注册表
    private final static MetricRegistry registry = new MetricRegistry();
    private final static Meter requestMeter = registry.meter("tps");
    private final static Meter sizeMeter = registry.meter("volume");

    public static void main(String[] args) {
        // 报告分离
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.MINUTES)
                .convertDurationsTo(TimeUnit.MINUTES).build();
        // 多久输出一次
        reporter.start(10,TimeUnit.SECONDS);
        for (;;){
            handleRequest(new byte[ThreadLocalRandom.current().nextInt(1000)]);
            randomSleep();
        }
    }


    private static void handleRequest(byte[] request){
        requestMeter.mark();
        sizeMeter.mark(request.length);
        randomSleep();

    }
    private static void randomSleep()  {
        try {
            TimeUnit.SECONDS.sleep((ThreadLocalRandom.current().nextInt(10)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
