package com.rsmurniteguh.bpjs.bpjsservice.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.EntityDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.exception.ServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.CommonServiceNoConfigProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.ResponseStsUtil;

@Configuration
public class BpjsConsumerConfig {

	private final CommonServiceNoConfigProxy commonServiceNoConfigProxy;
	private final BpjsConsumerService bpjsConsumerService;

	public BpjsConsumerConfig(CommonServiceNoConfigProxy commonServiceNoConfigProxy,
			BpjsConsumerService bpjsConsumerService) {
		this.commonServiceNoConfigProxy = commonServiceNoConfigProxy;
		this.bpjsConsumerService = bpjsConsumerService;
	}

	@Bean
	List<EntityDto> entityList() throws BusinessException, ServiceException {
		return ResponseStsUtil.handleResponseSts(commonServiceNoConfigProxy.getEntityList());
	}

	@Bean
	Map<String, String> entityTimeZone() throws BusinessException, ServiceException {
		List<EntityDto> entityList = entityList();
		Map<String, String> entityTimezone = new HashMap<>();
		for (EntityDto entityDto : entityList) {
			entityTimezone.put(entityDto.getEntityCode(), entityDto.getTimeZone());
		}
		return entityTimezone;
	}

	@Bean
	List<BpjsConsumerWithCategoryDto> bpjsConsumerWithCategoryList() throws BusinessException, ServiceException {
		List<String> entityCodeList = new ArrayList<>();
		entityList().forEach(entity -> entityCodeList.add(entity.getEntityCode()));
		return bpjsConsumerService.getBpjsConsumerWithCategoryList(null, entityCodeList);
	}
}
