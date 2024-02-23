package com.JHPay.settlement.job;

import com.JHPay.settlement.tasklet.SettlementTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("org.springframework.batch.core.configuration.annotation")
@RequiredArgsConstructor
public class SettlementJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SettlementTasklet settlementTasklet;

    @Bean
    public Job settlement() {
        return jobBuilderFactory.get("settlement")
                .start(settlementStep())
                .build();
    }

    public Step settlementStep() {
        return stepBuilderFactory.get("settlementStep")
                .tasklet(settlementTasklet)
                .build();
    }
}