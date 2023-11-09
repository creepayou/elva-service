package com.rsmurniteguh.bpjs.bpjsservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerCategoryDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerWithCategoryDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumer;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategory;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;
import com.rsmurniteguh.bpjs.bpjsservice.repository.BpjsConsumerRepository;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

@Service
public class BpjsConsumerServiceImpl implements BpjsConsumerService {

	private final BpjsConsumerRepository bpjsConsumerRepository;

	public BpjsConsumerServiceImpl(BpjsConsumerRepository bpjsConsumerRepository) {
		this.bpjsConsumerRepository = bpjsConsumerRepository;
	}

	@Override
	public BpjsConsumerDto insertBpjsConsumer(BpjsConsumerDto bpjsConsumerDto) {
		BpjsConsumer bpjsConsumer = bpjsConsumerDto.toBpjsConsumer();
		bpjsConsumerRepository.insert(bpjsConsumer);
		return BpjsConsumerDtoMapper.toBpjsConsumerDto(bpjsConsumer);
	}

	@Override
	public void updateBpjsConsumer(BpjsConsumerDto bpjsConsumerDto) {
		BpjsConsumer bpjsConsumer = bpjsConsumerDto.toBpjsConsumer();
		bpjsConsumerRepository.update(bpjsConsumer);
	}

	@Override
	public BpjsConsumerDto getBpjsConsumerByEntityCode(String entityCode) {
		return BpjsConsumerDtoMapper.toBpjsConsumerDto(bpjsConsumerRepository.getByEntityCode(entityCode));
	}

	@Override
	public String getProviderCodeByEntityCode(String entityCode) throws BusinessException {
		BpjsConsumerDto bpjsConsumerDto = getBpjsConsumerByEntityCode(entityCode);
		if (bpjsConsumerDto == null)
			throw new BusinessException("Invalid entity");
		return bpjsConsumerDto.getProviderCode();
	}

	@Override
	public BpjsConsumerCategoryDto insertBpjsConsumerCategory(BpjsConsumerCategoryDto bpjsConsumerCategoryDto) {
		BpjsConsumerCategory bpjsConsumerCategory = bpjsConsumerCategoryDto.toBpjsConsumerCategory();
		bpjsConsumerRepository.insertCategory(bpjsConsumerCategory);
		return BpjsConsumerCategoryDtoMapper.toBpjsConsumerCategoryDto(bpjsConsumerCategory);
	}

	@Override
	public List<BpjsConsumerWithCategoryDto> getBpjsConsumerWithCategoryList(BpjsConsumerCategoryType category,
			List<String> entityCodeList) {
		return BpjsConsumerWithCategoryDtoMapper.toBpjsConsumerWithCategoryDtoList(
				bpjsConsumerRepository.selectWithCategoryByEntityCodeList(category, entityCodeList));
	}
}
