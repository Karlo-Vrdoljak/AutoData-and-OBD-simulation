package com.obdsimulation.obdautodata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ObdAutodataApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ObdAutodataApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ObdAutodataApplication.class, args);
	}

}

