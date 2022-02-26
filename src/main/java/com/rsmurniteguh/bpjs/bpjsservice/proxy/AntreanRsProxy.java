package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "antreanrs", url = "${proxy.antreanrs.host}", configuration = {BpjsRequestConfig.class, BpjsRequestClientConfig.class})
public interface AntreanRsProxy {
    
}
