package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsFeignClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestErrorConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.ValidateICareDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse;

@FeignClient(name = Constant.IHS_FEIGN_NAME, url = "${proxy.ihs.host}", configuration = {
        BpjsRequestConfig.class, BpjsFeignClientConfig.class, BpjsRequestErrorConfig.class })
public interface IHSProxy {

    // #region Referensi

    @PostMapping("/api/rs/validate")
    public BpjsResponse<String> validate(
			@RequestBody ValidateICareDto validateICareDto, 
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    // #endregion
}
