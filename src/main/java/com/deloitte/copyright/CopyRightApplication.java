package com.deloitte.copyright;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * This is the main Spring Boot Application class which starts the CopyRight Application.
 * The Main intent of this Application is to provide an API which will 
 * take a String input from User and find and replace few words.
 * 
 * The API will be available at "/copyright" URL pattern which is defined in 
 * the @link(com.deloitte.copyright.controller.CopyRightController.class)
 * 
 * @author Yogendra Joshi
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.deloitte.copyright")
public class CopyRightApplication extends SpringBootServletInitializer {

	/*
     * Create required HandlerMapping, to avoid several default HandlerMapping instances being created
     */
    @Bean
    public HandlerMapping handlerMapping() {
        return new RequestMappingHandlerMapping();
    }

    /*
     * Create required HandlerAdapter, to avoid several default HandlerAdapter instances being created
     */
    @Bean
    public HandlerAdapter handlerAdapter() {
        return new RequestMappingHandlerAdapter();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(CopyRightApplication.class, args);
	}

}
