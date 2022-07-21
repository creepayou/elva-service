package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukBalikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukBalikDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rujukbalik")
@RestController
public class RujukBalikController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @Autowired
    private Map<String, String> entityTimeZone;

    private BpjsRequestDto<RequestRujukBalikDto> createRujukBalikRequest(RequestRujukBalikDto requestRujukBalikDto) {
        BpjsRequestDto<RequestRujukBalikDto> bpjsRequestDto = new BpjsRequestDto<>();
        bpjsRequestDto.getRequest().put("t_prb", requestRujukBalikDto);
        return bpjsRequestDto;
    }

    @PostMapping("/insert")
    public ResponseSts<BpjsRujukBalikDto> insertRujukBalik(@RequestBody RequestRujukBalikDto requestRujukBalikDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.insertRujukBalik(createRujukBalikRequest(requestRujukBalikDto), entityCode)));

    }

    @PutMapping("/update")
    public ResponseSts<String> updateRujukBalik(@RequestBody RequestRujukBalikDto requestRujukBalikDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.updateRujukBalik(createRujukBalikRequest(requestRujukBalikDto), entityCode)));

    }

    @DeleteMapping("/delete")
    public ResponseSts<String> deleteRujukBalik(@RequestBody RequestRujukBalikDto requestRujukBalikDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.updateRujukBalik(createRujukBalikRequest(requestRujukBalikDto), entityCode)));

    }

    @GetMapping("/getRujukBalik")
    public ResponseSts<BpjsRujukBalikDto> getRujukBalik(@RequestParam("noSrb") String noSrb,
            @RequestParam("noSep") String noSep, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getRujukBalik(noSrb, noSep, entityCode)).get("prb"));

    }

    @GetMapping("/getRujukBalikList")
    public ResponseSts<List<BpjsRujukBalikDto>> getRujukBalikList(@RequestParam("tglMulai") Timestamp tglMulai,
            @RequestParam("tglAkhir") Timestamp tglAkhir, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getRujukBalikList(
                        DateUtil.formatTimestampWithTimezone(tglMulai, entityTimeZone.get(entityCode)),
                        DateUtil.formatTimestampWithTimezone(tglAkhir, entityTimeZone.get(entityCode)), entityCode))
                .get("prb").getList());

    }
}
