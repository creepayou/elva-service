package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;

@FeignClient(name = "aplicares", url = "${proxy.aplicaresrest.host}", configuration = BpjsRequestConfig.class)
public interface AplicaresProxy {

	@GetMapping("/bed/read/0038R091/1/1000")
	public Object getListKamar(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

}
