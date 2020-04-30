package com.lcyanxi.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class UserSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {

    private static final  int TABLE_NUM = 4;

    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        System.out.println("availableTargetNames:"+availableTargetNames);
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % TABLE_NUM + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % TABLE_NUM + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
            Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
            Range<Integer> range = shardingValue.getValueRange();
            for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
                for (String each : availableTargetNames) {
                    if (each.endsWith(i % TABLE_NUM + "")) {
                        result.add(each);
                    }
                }
            }
            return result;
        }
}
