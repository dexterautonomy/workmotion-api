package com.workmotion.devops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableSwagger2
@SuppressWarnings("deprecation")
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.workmotion.devops"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("WorkMotion API", "Swagger API documentation", "1.0", "Terms of service",
				"Integration@WorkMotion", "Apache License Version 2.0", "https://www.workmotion.com/");

		return apiInfo;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
		registry.addRedirectViewController("/api/swagger-resources/configuration/ui",
				"/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/api/swagger-resources/configuration/security",
				"/swagger-resources/configuration/security");
		registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/api/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/api/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}