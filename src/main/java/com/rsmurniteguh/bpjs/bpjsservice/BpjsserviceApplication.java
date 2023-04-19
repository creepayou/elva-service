package com.rsmurniteguh.bpjs.bpjsservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.EntityDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.CommonServiceNoConfigProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.ResponseStsUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableFeignClients
public class BpjsserviceApplication {

	@Value("${app.name}")
	private String applicationName;

	@Value("${app.version}")
	private String applicationVersion;

	@Value("${app.desc}")
	private String applicationDescription;

	@Autowired
	private CommonServiceNoConfigProxy commonServiceNoConfigProxy;

	@Autowired
	private BpjsConsumerService bpjsConsumerService;

	public static void main(String[] args) {
		SpringApplication.run(BpjsserviceApplication.class, args);
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

	@Bean
	public List<EntityDto> entityList() throws BusinessException, ServiceException {
		return ResponseStsUtil.handleResponseSts(commonServiceNoConfigProxy.getEntityList());
	}

	@Bean
	public Map<String, String> entityTimeZone() throws BusinessException, ServiceException {
		List<EntityDto> entityList = entityList();
		Map<String, String> entityTimezone = new HashMap<>();
		for (EntityDto entityDto : entityList) {
			entityTimezone.put(entityDto.getEntityCode(), entityDto.getTimeZone());
		}
		return entityTimezone;
	}

	@Bean
	public List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList() throws BusinessException, ServiceException {
		List<String> entityCodeList = new ArrayList<>();
		entityList().forEach(entity -> entityCodeList.add(entity.getEntityCode()));
		return bpjsConsumerService
				.getBpjsConsumerWithCategoryList(null, entityCodeList);
	}
}
