package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKodeKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsListKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDeleteDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AplicaresProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.AplicaresResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/aplicares")
public class AplicaresController extends BaseController {

    @Autowired
    private AplicaresProxy aplicaresProxy;

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    private static final String LIST = "list";

    @GetMapping("/listKamar")
    public ResponseSts<List<BpjsListKamarDto>> getListKamar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<BpjsListKamarDto>> response = AplicaresResponseUtil.handleAplicaresResponse(
                    aplicaresProxy.getListKamar(bpjsConsumerService.getProviderCodeByEntityCode(entityCode),
                            entityCode));
            return ResponseSts.onSuccess(response.get(LIST));
        } catch(Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/kodeKamar")
    public ResponseSts<List<BpjsKodeKamarDto>> getKodeKamar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<BpjsKodeKamarDto>> response = AplicaresResponseUtil
                    .handleAplicaresResponse(aplicaresProxy.getKodeKamar(entityCode));
            return ResponseSts.onSuccess(response.get(LIST));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    private String getProviderCodeByEntityCode(String entityCode) {
        return bpjsConsumerService.getProviderCodeByEntityCode(entityCode);
    }

    @PostMapping("/createRoom")
    public ResponseSts<String> createRoom(
            @RequestBody RequestAplicaresDto requestAplicaresDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            requestAplicaresDto.setTersediapria("0");
            requestAplicaresDto.setTersediapriawanita("0");
            requestAplicaresDto.setTersediawanita("0");
            return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(aplicaresProxy
                    .createRoom(getProviderCodeByEntityCode(entityCode), requestAplicaresDto, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }

    }

    @PostMapping("/updateRoom")
    public ResponseSts<String> updateRoom(
            @RequestBody RequestAplicaresDto requestAplicaresDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            requestAplicaresDto.setTersediapria("0");
            requestAplicaresDto.setTersediapriawanita("0");
            requestAplicaresDto.setTersediawanita("0");
            return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(
                    aplicaresProxy.updateRoom(getProviderCodeByEntityCode(entityCode),
                            requestAplicaresDto, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }

    }

    @PostMapping("/deleteRoom")
    public ResponseSts<String> deleteRoom(
            @RequestBody RequestAplicaresDeleteDto requestAplicaresDeleteDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(
                    aplicaresProxy.deleteRoom(getProviderCodeByEntityCode(entityCode),
                            requestAplicaresDeleteDto, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }

    }
}
