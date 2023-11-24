package com.dev.lsy.batchservice.config;

import com.dev.lsy.batchservice.domain.Customer;
import com.dev.lsy.batchservice.listener.StopWatchjobListener;
import com.dev.lsy.batchservice.mapper.CustomRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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
                .<Customer, Customer>chunk(100)
                .reader(pagingItemReader2())
                .writer(new ItemWriter<Customer>() {
                    @Override
                    public void write(List<? extends Customer> items) throws Exception {
                        log.info("this is writer~~~");
                        items.forEach(i -> log.info("item ==> [{}]", i));
                    }
                })
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
