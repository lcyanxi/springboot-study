package com.lcyanxi.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


    @Autowired
    private Database0Config database0Config;

//
//    @Autowired
//    private DatabaseShardingAlgorithm databaseShardingAlgorithm;

    @Autowired
    private UserSingleKeyTableShardingAlgorithm tableShardingAlgorithm;



    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        //分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(1);
        //添加两个数据库database0和database1
        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
        //设置默认数据库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, database0Config.getDatabaseName());

        //分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
        TableRule orderTableRule = TableRule.builder("pr_user_lesson")
                .actualTables(Arrays.asList("pr_user_lesson_0", "pr_user_lesson_1","pr_user_lesson_2","pr_user_lesson_3"))
                .dataSourceRule(dataSourceRule)
                .build();
        //分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(orderTableRule))
//                .databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy("user_id", tableShardingAlgorithm)).build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

}
