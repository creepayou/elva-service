package com.rsmurniteguh.bpjs.bpjsservice.controller;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/rujukan")
public class RujukanController extends BaseController{

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getRujukanByNoRujukan/{noRujukan}")
    public ResponseSts<BpjsRujukanDto> getRujukanByNoRujukan(@PathVariable("noRujukan") String noRujukan, @RequestHeader(Constant.ENTITY) String entityCode){
        try{
            return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.getRujukanRsByNoRujukan(noRujukan, entityCode)));
        } catch(Exception e){
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getRujukanByBpjsNo/{bpjsNo}")
    public ResponseSts<BpjsRujukanDto> getRujukanByBpjsNo(@PathVariable("bpjsNo") String bpjsNo, @RequestHeader(Constant.ENTITY) String entityCode){
        try{
            return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.getRujukanRsByNoKartu(bpjsNo, entityCode)));
        } catch(Exception e){
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getRujukanListByBpjsNo/{bpjsNo}")
    public ResponseSts<BpjsRujukanListDto> getRujukanListByBpjsNo (@PathVariable("bpjsNo") String bpjsNo, @RequestHeader(Constant.ENTITY) String entityCode){
        try{
            return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy.getRujukanRsListByNoKartu(bpjsNo, entityCode)));
        } catch(Exception e){
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
