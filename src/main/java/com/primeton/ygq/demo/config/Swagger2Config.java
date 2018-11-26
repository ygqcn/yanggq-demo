package com.primeton.ygq.demo.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.primeton.ygq.controller")).paths(PathSelectors.any())
				.build().securitySchemes(securitySchemes()).securityContexts(securityContexts());

	}

	/**
	 * 配置认证模式
	 */
	private List<ApiKey> securitySchemes() {
		return newArrayList(new ApiKey("Authorization", "Authorization", "header"));
	}

	/**
	 * 配置认证上下文
	 */
	private List<SecurityContext> securityContexts() {
		return newArrayList(
				SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build());
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}

	/**
	 * 项目信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ygq-demo").description("普元测试接口").version("1.0").build();
	}

}
