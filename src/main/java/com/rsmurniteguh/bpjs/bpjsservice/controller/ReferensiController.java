package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.apachecommons.CommonsLog;

@RestController
@RequestMapping("/referensi")
@CommonsLog
public class ReferensiController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getDiagnosa")
    public ResponseSts<List<VClaimMappingDto>> getDiagnosa(@RequestParam("diagnosa") String paramDiagnosa,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getDiagnosa(paramDiagnosa, entityCode));
            return ResponseSts.onSuccess(response.get("diagnosa"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getFaskes")
    public ResponseSts<List<VClaimMappingDto>> getFaskes(@RequestParam("faskes") String paramFaskes,
            @RequestParam("jenisFaskes") Faskes jenisFaskes, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getFaskes(paramFaskes, jenisFaskes.getJenis(), entityCode));
            return ResponseSts.onSuccess(response.get("faskes"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getPoli")
    public ResponseSts<List<VClaimMappingDto>> getPoli(@RequestParam("poli") String paramPoli,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getPoli(paramPoli, entityCode));
            return ResponseSts.onSuccess(response.get("poli"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getDokterDPJP")
    public ResponseSts<List<VClaimMappingDto>> getDokterDPJP(@RequestParam("jenisPelayanan") String jenisPelayanan,
            @RequestParam("tglPelayanan") Timestamp tglPelayanan, @RequestParam("spesialis") String spesialis,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.getDokterDPJP(JenisPelayanan.getJenisPelayanan(jenisPelayanan).getJenis().getKode(),
                            DateUtil.formatTimestampWithTimezone(tglPelayanan, Constant.TIMEZONE_JKT), spesialis,
                            entityCode));
            return ResponseSts.onSuccess(response.get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getPropinsi")
    public ResponseSts<List<VClaimMappingDto>> getPropinsi(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getPropinsi(entityCode));
            return ResponseSts.onSuccess(response.get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getKabupaten")
    public ResponseSts<List<VClaimMappingDto>> getKabupaten(@RequestParam("kodePropinsi") String kodePropinsi,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getKabupaten(kodePropinsi, entityCode));
            return ResponseSts.onSuccess(response.get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getKecamatan")
    public ResponseSts<List<VClaimMappingDto>> getKecamatan(@RequestParam("kodeKabupaten") String kodeKabupaten,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {
            Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getKecamatan(kodeKabupaten, entityCode));
            return ResponseSts.onSuccess(response.get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }

    @GetMapping("/getAsalFaskes/{providerCode}")
    public ResponseSts<Faskes> getAsalFaskes(@PathVariable("providerCode") String providerCode, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
        try {
            ResponseSts<List<VClaimMappingDto>> responseFaskes2 = getFaskes(providerCode, Faskes.FASKES_2, entityCode);
            if(responseFaskes2.isSuccess()){
                return ResponseSts.onSuccess(Faskes.FASKES_2);
            }
            return ResponseSts.onSuccess(Faskes.FASKES_1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
}
