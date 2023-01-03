package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsApotekRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsDphoDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = Constant.APOTEK_FEIGN_NAME, url = "${proxy.apotekrest.host}", configuration = {
		BpjsApotekRequestConfig.class, BpjsRequestClientConfig.class })
public interface ApotekProxy {

	@GetMapping(value = "/referensi/dpho")
	public BpjsResponse<List<BpjsDphoDto>> getDpho(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

}
