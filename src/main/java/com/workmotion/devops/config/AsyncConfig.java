package com.workmotion.devops.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class AsyncConfig implements AsyncConfigurer {
	private final ConfigProcessor CONFIG;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CONFIG.getCorePoolSize());
		executor.setMaxPoolSize(CONFIG.getMaxPoolSize());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix(CONFIG.getThreadPrefix());
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (exception, method, params) -> log.error(
		"---->>>> Exception of TYPE: {} occured in method: {}, "
		+ "MESSAGE: {}, CAUSED BY: {}", exception.getCause(),
		method.getName(), exception.getMessage(), exception.getCause());
	}
}
