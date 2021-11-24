package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/bpjsconsumer")
public class BpjsConsumerController extends BaseController {

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @GetMapping("/getProviderCode")
    public ResponseSts<String> getProviderCode(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts.onSuccess(bpjsConsumerService.getProviderCodeByEntityCode(entityCode));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/isBpjsConsumerAvailable")
    public ResponseSts<Boolean> isBpjsConsumerAvailable(@RequestParam("entityCode") String entityCode) {
        try {
            return ResponseSts.onSuccess(bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode) != null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
