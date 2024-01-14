package dev.digitalcodex.web.application.configuration;

import dev.digitalcodex.web.application.ApplicationConstants;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration(ApplicationConstants.ASYNC_CONFIGURATION_BEAN_NAME)
public class AsyncConfiguration {
    @Bean({"applicationTaskExecutor", "taskExecutor"})
    public TaskExecutor taskExecutor(TaskExecutorBuilder builder) {
        return builder.corePoolSize(2).maxPoolSize(2).queueCapacity(500).build();
    }
}
