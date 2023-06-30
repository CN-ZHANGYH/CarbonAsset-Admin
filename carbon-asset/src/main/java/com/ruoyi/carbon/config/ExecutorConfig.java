package com.ruoyi.carbon.config;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAsync
@Configurable
public class ExecutorConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setThreadNamePrefix("carbon-asset-executor");
        taskExecutor.setKeepAliveSeconds(30);
        taskExecutor.setQueueCapacity(100);
        return taskExecutor;
    }

    @Bean("taskScheduler")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
        poolTaskScheduler.setPoolSize(5);
        poolTaskScheduler.setThreadNamePrefix("carbon-asset-scheduler");
        poolTaskScheduler.setAwaitTerminationSeconds(30);
        poolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return poolTaskScheduler;
    }
}
