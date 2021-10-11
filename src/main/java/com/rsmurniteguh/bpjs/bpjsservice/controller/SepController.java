package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/sep")
public class SepController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @GetMapping("/searchSEP/{sepNo}")
    public ResponseSts<BpjsSepDto> searchSEP(@PathVariable("sepNo") String sepNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts
                    .onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.searchSEP(sepNo, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @PostMapping("/insertSEP")
    public ResponseSts<BpjsSepDto> insertSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            String providerCode = bpjsConsumerService.getProviderCodeByEntityCode(entityCode);
            return ResponseSts
                    .onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.insertSEP(requestSepDto.setPpkPelayanan(providerCode), entityCode)).get("sep"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @PutMapping("/updateSEP")
    public ResponseSts<String> updateSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts
                    .onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.updateSEP(requestSepDto, entityCode)).get("sep"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteSEP")
    public ResponseSts<String> deleteSEP(@RequestBody RequestSepDto requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts
                    .onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.deleteSEP(requestSepDto, entityCode)).get("sep"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
