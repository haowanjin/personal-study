package com.ddup.springbootquartz.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzFactoryConfig {

    @Bean
    public SchedulerFactoryBean SchedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler;
    }
}
