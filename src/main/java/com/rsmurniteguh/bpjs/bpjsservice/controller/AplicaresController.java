package com.rsmurniteguh.bpjs.bpjsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AplicaresProxy;

@RestController
@RequestMapping("/aplicares")
public class AplicaresController extends BaseController {

    @Autowired
    private AplicaresProxy aplicaresProxy;

    @GetMapping("/listKamar")
    public ResponseSts<Object> getListKamar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(aplicaresProxy.getListKamar(entityCode));
    }
    

}
