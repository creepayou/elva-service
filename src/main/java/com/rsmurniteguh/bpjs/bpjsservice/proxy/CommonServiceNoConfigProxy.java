package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.EntityDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//Used for bean initialization that doesn't require entity code to request
@FeignClient(name = "commonservicenoconfig", url = "${proxy.commonservice.host}")
public interface CommonServiceNoConfigProxy {
    
    @GetMapping("/entity/getEntityList")
    public ResponseSts<List<EntityDto>> getEntityList();
}
