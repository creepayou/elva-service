package com.rsmurniteguh.bpjs.bpjsservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import feign.Client;

public class BpjsRequestClientConfig {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;
    
    // @Bean
    // public Client client() {
    //     return new FeignClientConfig(null, null, bpjsConsumerService);
    // }
}
