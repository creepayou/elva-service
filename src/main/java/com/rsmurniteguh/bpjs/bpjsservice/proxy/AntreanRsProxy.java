package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestClientConfig;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPoliDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = Constant.ANTREAN_FEIGN_NAME, url = "${proxy.antreanrs.host}", configuration = {
        BpjsRequestConfig.class, BpjsRequestClientConfig.class })
public interface AntreanRsProxy {

    @GetMapping(value = "/ref/poli")
    public BpjsResponse2<List<BpjsAntreanPoliDto>> getReferensiPoli(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

    @GetMapping(value = "/ref/dokter")
    public BpjsResponse2<List<BpjsAntreanDoctorDto>> getReferensiDokter(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
}
