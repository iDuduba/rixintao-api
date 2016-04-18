package com.xiaochun.tao.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RixintaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RixintaoApiApplication.class, args);
	}
	
	@Bean
	public ApplicationListener<ContextRefreshedEvent> onContextRefeshed() {
		return new ApplicationListener<ContextRefreshedEvent>() {
			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {
				log.debug("Initializating application...");
			}
		};
	}
	
}
