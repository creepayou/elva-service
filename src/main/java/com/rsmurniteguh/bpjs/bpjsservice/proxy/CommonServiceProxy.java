package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import com.rsmurniteguh.bpjs.bpjsservice.config.FeignRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commonservice", url = "${proxy.commonservice.host}", configuration = FeignRequestConfig.class)
public interface CommonServiceProxy {
    
    @GetMapping("/parameter/getParameterValue")
    public ResponseSts<String> getParameterValue(@RequestParam("parameterName") String parameterName);
}