package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@CommonsLog
@RequestMapping("/monitoring")
public class MonitoringController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getKunjungan")
    public ResponseSts<List<BpjsKunjunganDto>> getKunjungan(@RequestParam("tanggalSEP") String tglSEP,
            @RequestParam("jenisPelayanan") String jnsPelayanan,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            return ResponseSts.Success(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDataKunjungan(tglSEP, JenisPelayanan.getJenisPelayanan(jnsPelayanan).getJenis().getKode(), entityCode)).get("sep"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getDataKlaim")
    public ResponseSts<List<BpjsKlaimDto>> getDataKlaim(@RequestParam("tanggalPulang") String tglPulang,
            @RequestParam("jenisPelayanan") JenisPelayanan jnsPelayanan,
            @RequestParam("statusKlaim") StatusKlaim statusKlaim,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            return ResponseSts.Success(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDataKlaim(tglPulang, jnsPelayanan.getJenis().getKode(), statusKlaim.getStatus().getKode(), entityCode)).get("klaim"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getHistori")
    public ResponseSts<List<BpjsKunjunganDto>> getHistori(@RequestParam("noBPJS") String noKartu,
            @RequestParam("tglAwal") Timestamp tglAwal,
            @RequestParam("tglAkhir") Timestamp tglAkhir,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            return ResponseSts.Success(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getHistoriPelayanan(noKartu, DateUtil.formatTimestamp(tglAwal), DateUtil.formatTimestamp(tglAkhir), entityCode)).get("histori"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }
}
