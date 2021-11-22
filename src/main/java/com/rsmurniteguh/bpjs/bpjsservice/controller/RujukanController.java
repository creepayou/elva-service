package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisRujukan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
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
@RequestMapping("/rujukan")
public class RujukanController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @GetMapping("/getRujukanByNoRujukan/{jenisRujukan}/{noRujukan}")
    public ResponseSts<BpjsRujukanDto> getRujukanByNoRujukan(@PathVariable("jenisRujukan") JenisRujukan jenisRujukan,
            @PathVariable("noRujukan") String noRujukan, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            if (jenisRujukan.equals(JenisRujukan.PCARE)) {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.getRujukanPCareByNoRujukan(noRujukan, entityCode)));
            }
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getRujukanRsByNoRujukan(noRujukan, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getRujukanByBpjsNo/{jenisRujukan}/{bpjsNo}")
    public ResponseSts<BpjsRujukanDto> getRujukanByBpjsNo(@PathVariable("jenisRujukan") JenisRujukan jenisRujukan,
            @PathVariable("bpjsNo") String bpjsNo, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            if (jenisRujukan.equals(JenisRujukan.PCARE)) {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.getRujukanPCareByNoKartu(bpjsNo, entityCode)));
            }
            return ResponseSts.onSuccess(
                    VClaimResponseUtil.handleVClaimResponse(vClaimProxy.getRujukanRsByNoKartu(bpjsNo, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getRujukanListByBpjsNo/{jenisRujukan}/{bpjsNo}")
    public ResponseSts<BpjsRujukanListDto> getRujukanListByBpjsNo(
            @PathVariable("jenisRujukan") JenisRujukan jenisRujukan, @PathVariable("bpjsNo") String bpjsNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            if (jenisRujukan.equals(JenisRujukan.PCARE)) {
                return ResponseSts.onSuccess(VClaimResponseUtil
                        .handleVClaimResponse(vClaimProxy.getRujukanPCareListByNoKartu(bpjsNo, entityCode)));
            }
            return ResponseSts.onSuccess(
                    VClaimResponseUtil.handleVClaimResponse(vClaimProxy.getRujukanRsListByNoKartu(bpjsNo, entityCode)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    private BpjsRequestDto<RequestRujukanDto> createBpjsRequestRujukan(RequestRujukanDto requestRujukanDto) {
        BpjsRequestDto<RequestRujukanDto> requestRujukan = new BpjsRequestDto<>();
        requestRujukan.getRequest().put("t_rujukan", requestRujukanDto);
        return requestRujukan;
    }

    @PostMapping("/insertRujukan")
    public ResponseSts<BpjsRujukanDto> insertRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            String ppkDirujuk = bpjsConsumerService.getProviderCodeByEntityCode(entityCode);
            requestRujukanDto.setPpkDirujuk(ppkDirujuk);
            return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.insertRujukan(createBpjsRequestRujukan(requestRujukanDto), entityCode)));
        } catch (Exception e) {
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @PutMapping("/updateRujukan")
    public ResponseSts<String> updateRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(
                            vClaimProxy.updateRujukan(createBpjsRequestRujukan(requestRujukanDto), entityCode))
                    .get("rujukan"));
        } catch (Exception e) {
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteRujukan")
    public ResponseSts<String> deleteRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(
                            vClaimProxy.deleteRujukan(createBpjsRequestRujukan(requestRujukanDto), entityCode))
                    .get("rujukan"));
        } catch (Exception e) {
            return ResponseSts.onFail(e.getMessage());
        }
    }
}