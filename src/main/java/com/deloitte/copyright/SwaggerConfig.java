package com.deloitte.copyright;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration File for the CopyRight API which 
 * can be used for API Documentation 
 * 
 * @author Yogendra Joshi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
	        new HashSet<>(Arrays.asList("application/json")); 
	
	/**
	 * This method constructs a Docket which scans all the API 
	 * classes within the CopyRight API codebase
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInfo())
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title("CopyRight API")
								   .description("Takes the user input and sends it to CopyRightService to "
								   		+ "find and replace few words with Copyright Symbols")
								   .build();
	}
}
