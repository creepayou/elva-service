package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AntreanRsProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/antreanrs")
public class AntreanRsController extends BaseController {

    @Autowired
    private AntreanRsProxy antreanRsProxy;

    @GetMapping("/getReferensiPoli")
    public ResponseSts<Object> getReferensiPoli(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiPoli(entityCode)));
    }
}