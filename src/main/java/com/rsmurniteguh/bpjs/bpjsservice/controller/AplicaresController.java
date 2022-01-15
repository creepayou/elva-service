package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAplicaresCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKodeKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsListKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDeleteDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AplicaresProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.AplicaresResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

@RestController
@RequestMapping("/aplicares")
public class AplicaresController extends BaseController {

    @Autowired
    private AplicaresProxy aplicaresProxy;
    
    private static final String LIST = "list";

    @GetMapping("/listKamar")
    public ResponseSts<List<BpjsListKamarDto>> getListKamar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	Map<String, List<BpjsListKamarDto>> response = AplicaresResponseUtil.handleAplicaresResponse(aplicaresProxy.getListKamar(entityCode));
    	return ResponseSts.onSuccess(response.get(LIST));
    }
    
    @GetMapping("/kodeKamar")
    public ResponseSts<List<BpjsKodeKamarDto>> getKodeKamar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	Map<String, List<BpjsKodeKamarDto>> response = AplicaresResponseUtil.handleAplicaresResponse(aplicaresProxy.getKodeKamar(entityCode));
    	return ResponseSts.onSuccess(response.get(LIST));
    }
    
    private <T> BpjsRequestDto2<T> createAplicaresRequest(T request) {
    	BpjsRequestDto2<T> bpjsRequest = new BpjsRequestDto2<>();
    	bpjsRequest.setRequest(request);
    	return bpjsRequest;
    }
    
    @PostMapping("/createRoom")
    public ResponseSts<String> createRoom(
            @RequestBody RequestAplicaresDto requestAplicaresDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	requestAplicaresDto.setTersediapria("0");
    	requestAplicaresDto.setTersediapriawanita("0");
    	requestAplicaresDto.setTersediawanita("0");
        return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(aplicaresProxy.createRoom(requestAplicaresDto,entityCode)));

    }
    
    @PostMapping("/updateRoom")
    public ResponseSts<String> updateRoom(
            @RequestBody RequestAplicaresDto requestAplicaresDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	requestAplicaresDto.setTersediapria("0");
    	requestAplicaresDto.setTersediapriawanita("0");
    	requestAplicaresDto.setTersediawanita("0");
        return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(aplicaresProxy.updateRoom(requestAplicaresDto,entityCode)));

    }
    
    @PostMapping("/deleteRoom")
    public ResponseSts<String> deleteRoom(
            @RequestBody RequestAplicaresDeleteDto requestAplicaresDeleteDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(AplicaresResponseUtil.handleAplicaresResponseMessage(aplicaresProxy.deleteRoom(requestAplicaresDeleteDto,entityCode)));

    }
}
