package com.TrainingCenter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application Config
 */
@Configuration
public class ApplicationConfig {

	/**
	 * Model mapping bean
	 * 
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}
}
