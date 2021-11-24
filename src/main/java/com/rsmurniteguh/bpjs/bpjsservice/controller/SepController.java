package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.io.IOException;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.model.VClaimVersion;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.ObjectUtil;
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

    private BpjsRequestDto<RequestSepDto> createBpjsRequestSep(RequestSepDtoV2 requestSepDtoV2) throws IOException {
        RequestSepDto requestSepDto = ObjectUtil.convertObjectToClass(requestSepDtoV2, RequestSepDto.class);
        requestSepDto.setKlsRawat(requestSepDtoV2.getKlsRawat().getKlsRawatHak());

        BpjsRequestDto<RequestSepDto> requestSep = new BpjsRequestDto<>();
        requestSep.getRequest().put("t_sep", requestSepDto);
        return requestSep;
    }

    private BpjsRequestDto<RequestSepDtoV2> createBpjsRequestSepV2(RequestSepDtoV2 requestSepDto) {
        BpjsRequestDto<RequestSepDtoV2> requestSep = new BpjsRequestDto<>();
        requestSep.getRequest().put("t_sep", requestSepDto);
        return requestSep;
    }

    private VClaimVersion getVclaimVersion(String entityCode) {
        return bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode).getVclaimVersion();
    }

    @PostMapping("/insertSEP")
    public ResponseSts<BpjsSepDto> insertSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            String providerCode = bpjsConsumerService.getProviderCodeByEntityCode(entityCode);
            requestSepDto.setPpkPelayanan(providerCode);

            VClaimVersion vclaimVersion = getVclaimVersion(entityCode);
            if (vclaimVersion.equals(VClaimVersion.V2)) {
                return ResponseSts
                        .onSuccess(VClaimResponseUtil
                                .handleVClaimResponse(
                                        vClaimProxy.insertSEPV2(createBpjsRequestSepV2(requestSepDto), entityCode))
                                .get("sep"));
            } else {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.insertSEP(createBpjsRequestSep(requestSepDto), entityCode))
                        .get("sep"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @PutMapping("/updateSEP")
    public ResponseSts<String> updateSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            VClaimVersion vclaimVersion = getVclaimVersion(entityCode);
            if (vclaimVersion.equals(VClaimVersion.V2)) {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.updateSEPV2(createBpjsRequestSepV2(requestSepDto), entityCode))
                        .get("sep"));
            } else {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.updateSEP(createBpjsRequestSep(requestSepDto), entityCode))
                        .get("sep"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteSEP")
    public ResponseSts<String> deleteSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            VClaimVersion vclaimVersion = getVclaimVersion(entityCode);
            if (vclaimVersion.equals(VClaimVersion.V2)) {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.deleteSEPV2(createBpjsRequestSepV2(requestSepDto), entityCode))
                        .get("sep"));
            } else {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.deleteSEP(createBpjsRequestSep(requestSepDto), entityCode))
                        .get("sep"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
