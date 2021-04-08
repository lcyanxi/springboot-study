package com.lcyanxi.business;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * addShutdownHook:优雅关闭线程池
 * @author lichang
 * @date 2021/4/8
 */
@Slf4j
public class AddShutdownHookDemo {

    @Autowired
    private RedisTemplate<String, String> redisClient;

    private ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
    private static ConcurrentHashMap<Thread, ThreadLockInfo> THREAD_HOLD = new ConcurrentHashMap<>();
    private static final int TTL_TIME = 60;
    private volatile boolean poolExecute = false;
    private static final int CHECK_TIME_SEC = 10;

    public static void main(String[] args) {
        System.out.println("starting working......");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                int nextInt = ThreadLocalRandom.current().nextInt(500,1000);
                TimeUnit.MILLISECONDS.sleep(nextInt);
                log.info("执行钩子线程 sleep :[{}]ms",nextInt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }));
        System.out.println("program ending");
        AddShutdownHookDemo demo = new AddShutdownHookDemo();
        demo.startTTLDaemon();
    }


    private  void startTTLDaemon() {
        log.info("ConcurrentLeaseLock TTL daemon thread start");
        if (!poolExecute) {
            synchronized (this) {
                if (!poolExecute) {
                    poolExecute = true;
                    pool.scheduleWithFixedDelay(() -> {
                        List<Thread> deadThreads = Lists.newArrayList();
                        THREAD_HOLD.forEach((t, info) -> {
                            if (Objects.nonNull(t) && Objects.nonNull(info)) {
                                if (t.isAlive()) {
                                    try {
                                        String redisRandomValue = redisClient.opsForValue().get(info.lockName);
                                        if (info.seed.equals(redisRandomValue)) {
                                            redisClient.expire(info.lockName, TTL_TIME,TimeUnit.SECONDS);
                                        }
                                    } catch (Exception e) {
                                        log.error("ConcurrentLeaseLock redis down");
                                    }
                                } else {
                                    deadThreads.add(t);
                                }
                            }
                        });
                        deadThreads.forEach(deadThread -> {
                            THREAD_HOLD.remove(deadThread);
                        });

                    }, 1, CHECK_TIME_SEC, TimeUnit.SECONDS);
                }
            }
        }
    }

    @Builder
    @Data
    static class ThreadLockInfo {
        /**
         * 线程
         */
        private Thread thread;

        /**
         * 锁的key
         */
        private String lockName;

        /**
         * 随机数，who lock, who release
         */
        private String seed;

        /**
         * 任务在db上的锁的主键
         */
        private long dbKey;

    }
}
