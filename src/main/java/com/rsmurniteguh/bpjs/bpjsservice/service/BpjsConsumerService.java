package com.rsmurniteguh.bpjs.bpjsservice.service;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;

public interface BpjsConsumerService {

    BpjsConsumerDto insertBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);
    
    void updateBpjsConsumer(BpjsConsumerDto bpjsConsumerDto);
    
    BpjsConsumerDto getBpjsConsumerByEntityCode(String entityCode);
}
