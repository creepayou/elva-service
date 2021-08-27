package com.rsmurniteguh.bpjs.bpjsservice.service.impl;

import com.rsmurniteguh.bpjs.bpjsservice.dto.mapper.BpjsConsumerDtoMapper;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.repository.BpjsConsumerRepository;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BpjsConsumerServiceImpl implements BpjsConsumerService{

    @Autowired
    private BpjsConsumerRepository bpjsConsumerRepository;

    @Override
    public BpjsConsumerDto getBpjsConsumerByEntityCode(String entityCode) {
        return BpjsConsumerDtoMapper.toBpjsConsumerDto(bpjsConsumerRepository.getByEntityCode(entityCode));
    }

    
}
