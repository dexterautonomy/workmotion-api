package com.workmotion.devops.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("app.prop")
public class ConfigProcessor {
	private int corePoolSize;
	private int maxPoolSize;
	private String threadPrefix;
}