package com.rsmurniteguh.bpjs.bpjsservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import feign.Client;
import feign.Retryer;

public class BpjsRequestClientConfig {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;
    
    @Bean
    public Client client() {
        return new FeignClientConfig(null, null, bpjsConsumerService);
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(500L, 30000L, 3);
    }

    @Bean
    public BpjsRequestErrorConfig errorDecoder() {
        return new BpjsRequestErrorConfig();
    }
}
