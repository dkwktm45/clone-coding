package com.hodoleg.clonecoding;

import com.hodoleg.clonecoding.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class CloneCodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneCodingApplication.class, args);
	}

}
