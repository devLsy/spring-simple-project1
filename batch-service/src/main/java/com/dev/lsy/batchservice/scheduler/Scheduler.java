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

import java.time.LocalDateTime;

@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class Scheduler {

    private final JobLauncher launcher;
    private final Job batchJob;

    @Scheduled(cron = "* 50 09 * * *")
    public void jobRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        launcher.run(batchJob, new JobParametersBuilder()
                .addString("date", "param_" + LocalDateTime.now().toString())
                .toJobParameters()
        );
    }
}
