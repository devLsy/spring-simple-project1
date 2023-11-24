package com.dev.lsy.batchservice.batch.config;

import com.dev.lsy.batchservice.batch.domain.Customer;
import com.dev.lsy.batchservice.batch.listener.StopWatchjobListener;
import com.dev.lsy.batchservice.batch.mapper.CustomRowMapper;
import com.dev.lsy.batchservice.batch.tasklet.CustomTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class Job2Config {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    /**
     * job
     * @return
     * @throws Exception
     */
    @Bean
    public Job batch2() throws Exception {
        return jobBuilderFactory.get("batch2")
                .incrementer(new RunIdIncrementer())
                .start(batchStep2())
                .listener(new StopWatchjobListener())
                .build();
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    public Step batchStep2() throws Exception {
        return stepBuilderFactory.get("batchStep2")
                .tasklet(new CustomTasklet())
                .build();
    }

    /**
     * 동기 reader
     * @return
     */
    @Bean
    public JdbcPagingItemReader<Customer> pagingItemReader2() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(dataSource);
        reader.setFetchSize(300);
        reader.setRowMapper(new CustomRowMapper());

        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        queryProvider.setSelectClause("id, first_name, last_name, birthdate");
        queryProvider.setFromClause("from customer2");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        return reader;
    }
}
