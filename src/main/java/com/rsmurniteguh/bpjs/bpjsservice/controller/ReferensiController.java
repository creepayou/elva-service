package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
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
@RequestMapping("/referensi")
@CommonsLog
public class ReferensiController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getDiagnosa")
    public ResponseSts<List<VClaimMappingDto>> getDiagnosa(@RequestParam("diagnosa") String paramDiagnosa,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDiagnosa(paramDiagnosa, entityCode));
            return ResponseSts.Success(response.get("diagnosa"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getFaskes")
    public ResponseSts<List<VClaimMappingDto>> getFaskes(@RequestParam("faskes") String paramFaskes,
            @RequestParam("jenisFaskes") String jenisFaskes,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getFaskes(paramFaskes, Faskes.getJenisFaskes(jenisFaskes).getJenis(), entityCode));
            return ResponseSts.Success(response.get("faskes"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getPoli")
    public ResponseSts<List<VClaimMappingDto>> getPoli(@RequestParam("poli") String paramPoli,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getPoli(paramPoli, entityCode));
            return ResponseSts.Success(response.get("poli"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getDokterDPJP")
    public ResponseSts<List<VClaimMappingDto>> getDokterDPJP(@RequestParam("jenisPelayanan") String jenisPelayanan,
            @RequestParam("tglPelayanan") Timestamp tglPelayanan,
            @RequestParam("spesialis") String spesialis,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDokterDPJP(JenisPelayanan.getJenisPelayanan(jenisPelayanan).getJenis().getKode(), DateUtil.formatTimestamp(tglPelayanan), spesialis, entityCode));
            return ResponseSts.Success(response.get("list"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getPropinsi")
    public ResponseSts<List<VClaimMappingDto>> getPropinsi(@RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getPropinsi(entityCode));
            return ResponseSts.Success(response.get("list"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getKabupaten")
    public ResponseSts<List<VClaimMappingDto>> getKabupaten(@RequestParam("kodePropinsi") String kodePropinsi,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getKabupaten(kodePropinsi, entityCode));
            return ResponseSts.Success(response.get("list"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }

    @GetMapping("/getKecamatan")
    public ResponseSts<List<VClaimMappingDto>> getKecamatan(@RequestParam("kodeKabupaten") String kodeKabupaten,
            @RequestHeader(Constant.ENTITY) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getKecamatan(kodeKabupaten, entityCode));
            return ResponseSts.Success(response.get("list"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.Fail(e.getMessage());
        }
    }
}
