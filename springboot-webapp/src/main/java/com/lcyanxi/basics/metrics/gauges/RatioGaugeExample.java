package com.lcyanxi.basics.metrics.gauges;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 监控接口成功率
 * @author lichang
 * @date 2021/3/25
 */
public class RatioGaugeExample {
    // 创建一个注册表
    private static final MetricRegistry registry = new MetricRegistry();

    // 创建一个 ConsoleReporter
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private final static Meter totalMeter = new Meter();
    private final static Meter successMeter = new Meter();

    public static void main(String[] args) {
        // 每 10 秒向控制台报告一次。
        reporter.start(10,TimeUnit.SECONDS);

        // 向注册表中注册一个 ratioGauge。
        registry.gauge("success-rate",() -> new RatioGauge(){
            @Override
            protected Ratio getRatio() {
                return Ratio.of(successMeter.getCount(),totalMeter.getCount());
            }
        });

        for (;;){
            shortSleep();
            business();
        }
    }

    private static void business(){
        // total inc
        totalMeter.mark();
        try{
            int x= 1/ ThreadLocalRandom.current().nextInt(6);
            // success inc
            successMeter.mark();
        }catch (Exception e){
            System.out.println("error...");
        }
    }

    private static void shortSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
        } catch (InterruptedException e) {
        }
    }


}
