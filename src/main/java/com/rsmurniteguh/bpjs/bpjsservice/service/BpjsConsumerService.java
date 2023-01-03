package com.rsmurniteguh.bpjs.bpjsservice.service;

import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;

public interface BpjsConsumerService {

    BpjsConsumerDto insertBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);

    void updateBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);

    BpjsConsumerDto getBpjsConsumerByEntityCode(Map<String, Object> param);
    
    BpjsConsumerDto getBpjsConsumerByEntityCode2(Map<String, Object> param);

    String getProviderCodeByEntityCode(String entityCode) throws BusinessException;

    BpjsConsumerCategoryDto insertBpjsConsumerCategory(BpjsConsumerCategoryDto bpjsConsumerCategoryDto);

    List<BpjsConsumerWithCategoryDto> getBpjsConsumerWithCategoryList(BpjsConsumerCategoryType category,
            List<String> entityCodeList);
    
    List<BpjsConsumerDto> getBpjsConsumerList(List<String> entityCodeList);
}
