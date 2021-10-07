package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/bpjsConsumer")
public class BpjsConsumerController {
    
    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @GetMapping("/getProviderCode")
    public ResponseSts<String> getProviderCode(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
        try{
            return ResponseSts.onSuccess(bpjsConsumerService.getProviderCodeByEntityCode(entityCode));
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
