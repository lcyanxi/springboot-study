package com.lcyanxi.metrics.gauges;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.DerivativeGauge;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

/**
 * @author lichang
 * @date 2021/3/25
 */
public class DerivativeGaugeExample {
    // 本地缓存
    private static final LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(10) // 最大元素
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .recordStats() // 命中率、丢失率等数据
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return key.toUpperCase();
                }
            });


    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws InterruptedException {

        reporter.start(10,TimeUnit.SECONDS);
        Gauge cacheGauge = metricRegistry.gauge("cache-stats", () -> cache::stats);

        metricRegistry.register("missCount",new DerivativeGauge<CacheStats,Long>(cacheGauge){
            @Override
            protected Long transform(CacheStats cacheStats) {
                return cacheStats.missCount();
            }
        });
        metricRegistry.register("loadExceptionCount",new DerivativeGauge<CacheStats,Long>(cacheGauge){
            @Override
            protected Long transform(CacheStats cacheStats) {
                return cacheStats.loadExceptionCount();
            }
        });

        while (true){
            business();
            TimeUnit.SECONDS.sleep(1);
        }
    }


    private static void business(){
        String alex = cache.getUnchecked("alex");
        System.out.println(alex);
    }

}
