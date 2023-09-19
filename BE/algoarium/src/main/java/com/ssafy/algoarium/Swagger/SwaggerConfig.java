package com.ssafy.algoarium.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @Configuration
@Configuration
public class SwaggerConfig{

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30) // 3.0 문서버전으로 세팅
			.useDefaultResponseMessages(true)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Swagger 3.0 Api Sample")
			.description("This is Sample")
			.version("1.0")
			.build();
	}
}