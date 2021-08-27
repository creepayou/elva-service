package com.rsmurniteguh.bpjs.bpjsservice.service;

import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;

public interface BpjsConsumerService {
    
    BpjsConsumerDto getBpjsConsumerByEntityCode(String entityCode);
}
