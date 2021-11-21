package com.workmotion.devops.config;

import java.util.List;

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
	private List<String> contractType;
	private List<String> countries;
	private List<String> department;
	private List<String> gender;
	private List<String> maritalStatus;
	private List<String> qualification;
	private List<String> workType;
}