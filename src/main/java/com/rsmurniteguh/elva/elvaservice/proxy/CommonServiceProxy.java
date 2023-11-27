package com.rsmurniteguh.elva.elvaservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rsmurniteguh.elva.elvaservice.base.model.ResponseSts;
import com.rsmurniteguh.elva.elvaservice.config.FeignRequestConfig;

@FeignClient(name = "commonservice", url = "${proxy.commonservice.host}", configuration = FeignRequestConfig.class)
public interface CommonServiceProxy {

    @GetMapping("/parameter/getParameterValue")
    public ResponseSts<String> getParameterValue(@RequestParam("parameterName") String parameterName);
}
