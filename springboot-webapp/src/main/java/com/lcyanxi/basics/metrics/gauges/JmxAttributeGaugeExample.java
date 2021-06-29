package com.lcyanxi.basics.metrics.gauges;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxAttributeGauge;
import com.codahale.metrics.MetricRegistry;
import java.util.concurrent.TimeUnit;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author lichang
 * @date 2021/3/25
 */
public class JmxAttributeGaugeExample {
    // 创建一个注册表
    private static final MetricRegistry registry = new MetricRegistry();

    // 创建一个 ConsoleReporter
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException {
        reporter.start(10,TimeUnit.SECONDS);

        // 向注册表注册一个 JmxAttributeGauge（堆内存）
        registry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"HeapMemory"),
                new JmxAttributeGauge(new ObjectName("java.lang:type=Memory"),"HeapMemoryUsage"));

        // 向注册表注册一个 JmxAttributeGauge (非堆内存)
        registry.register(MetricRegistry.name(JmxAttributeGaugeExample.class,"NonHeapMemoryUsage"),
                new JmxAttributeGauge(new ObjectName("java.lang:type=Memory"),"NonHeapMemoryUsage"));

        Thread.currentThread().join();
    }
}
