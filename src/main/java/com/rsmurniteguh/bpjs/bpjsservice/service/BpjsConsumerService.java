package com.rsmurniteguh.bpjs.bpjsservice.service;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerWithCategoryDto;
import com.rsmurniteguh.bpjs.bpjsservice.model.BpjsConsumerCategoryType;

public interface BpjsConsumerService {

    BpjsConsumerDto insertBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);
    
    void updateBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);
    
    BpjsConsumerDto getBpjsConsumerByEntityCode(String entityCode);

    String getProviderCodeByEntityCode(String entityCode);

    BpjsConsumerCategoryDto insertBpjsConsumerCategory(BpjsConsumerCategoryDto bpjsConsumerCategoryDto);

    BpjsConsumerWithCategoryDto getBpjsConsumerWithCategory(BpjsConsumerCategoryType category, String entityCode);
}
