package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FilterTanggalRencanaKontrol;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisKontrol;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.DataDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/rencanakontrol")
public class RencanaKontrolController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getRencanaKontrol")
    public ResponseSts<List<RencanaKontrolDto>> getRencanaKontrol(@RequestParam("tglAwal") Timestamp tglAwal, 
    		@RequestParam("tglAkhir") Timestamp tglAkhir, 
    		@RequestParam("filter") FilterTanggalRencanaKontrol filter, 
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {           
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getRencanaKontrol(DateUtil.formatTimestampWithTimezone(tglAwal, Constant.TIMEZONE_JKT),
                    		DateUtil.formatTimestampWithTimezone(tglAkhir, Constant.TIMEZONE_JKT), filter.getFilter(), entityCode))
                    .get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
    @GetMapping("/getDataDokter")
    public ResponseSts<List<DataDokterDto>> getDataDokter(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol, 
    		@RequestParam("poli") String poli, 
    		@RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol, 
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {           
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDataDokter(jenisKontrol.getJenis(), poli,
                    		DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, Constant.TIMEZONE_JKT), entityCode))
                    .get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
