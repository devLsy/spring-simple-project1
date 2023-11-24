package com.dev.lsy.batchservice.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher launcher;
    private final Job batch1;
    private final Job batch2;

    @Scheduled(cron = "0 22 10 * * *")
    public void job1() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        launcher.run(batch1, new JobParametersBuilder()
                .addString("date", "param1_" + LocalDateTime.now().toString())
                .toJobParameters()
        );
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void job2() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        launcher.run(batch2, new JobParametersBuilder()
                .addString("date", "param2_" + LocalDateTime.now().toString())
                .toJobParameters()
        );
    }
}
