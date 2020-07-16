package com.cds.regression.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

  /**
   * Method which will create thread pool for parallel execution of tasks.
   * @return- ThreadPoolTaskExecutor.
  */
  @Bean
  public Executor taskExecutor() {
    
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(3);
    executor.setMaxPoolSize(3);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("dbThread-");
    executor.initialize();
    return executor;
  } // End of taskExecutor.
} // End of ThreadPoolConfig.