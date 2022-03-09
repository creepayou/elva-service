package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = Constant.ANTREAN_FEIGN_NAME, url = "${proxy.antreanrs.host}", configuration = {BpjsRequestConfig.class, BpjsRequestClientConfig.class})
public interface AntreanRsProxy {
    
    @GetMapping(value="/ref/poli")
    public BpjsResponse<Object> getReferensiPoli();
    
}
