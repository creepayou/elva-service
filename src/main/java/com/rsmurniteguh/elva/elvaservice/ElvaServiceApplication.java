package com.rsmurniteguh.elva.elvaservice;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.rsmurniteguh.elva.elvaservice.base.constant.Constant;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableFeignClients
public class ElvaServiceApplication {

	@Value("${app.name}")
	private String applicationName;

	@Value("${app.version}")
	private String applicationVersion;

	@Value("${app.desc}")
	private String applicationDescription;

	public static void main(String[] args) {
		SpringApplication.run(ElvaServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(Constant.TIMEZONE_UTC));
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info().title(applicationName)
						.description(applicationDescription)
						.version(applicationVersion));
	}

}
