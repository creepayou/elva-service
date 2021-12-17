package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
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

@RestController
@RequestMapping("/referensi")
public class ReferensiController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    private static final String LIST = "list";

    @GetMapping("/getDiagnosa")
    public ResponseSts<List<VClaimMappingDto>> getDiagnosa(@RequestParam("diagnosa") String paramDiagnosa,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getDiagnosa(paramDiagnosa, entityCode));
        return ResponseSts.onSuccess(response.get("diagnosa"));
    }

    @GetMapping("/getFaskes")
    public ResponseSts<List<VClaimMappingDto>> getFaskes(@RequestParam("faskes") String paramFaskes,
            @RequestParam("jenisFaskes") Faskes jenisFaskes,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getFaskes(paramFaskes, jenisFaskes.getJenis(), entityCode));
        return ResponseSts.onSuccess(response.get("faskes"));
    }

    @GetMapping("/getPoli")
    public ResponseSts<List<VClaimMappingDto>> getPoli(@RequestParam("poli") String paramPoli,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getPoli(paramPoli, entityCode));
        return ResponseSts.onSuccess(response.get("poli"));
    }

    @GetMapping("/getDokterDPJP")
    public ResponseSts<List<VClaimMappingDto>> getDokterDPJP(@RequestParam("jenisPelayanan") String jenisPelayanan,
            @RequestParam("tglPelayanan") Timestamp tglPelayanan, @RequestParam("spesialis") String spesialis,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil.handleVClaimResponse(
                vClaimProxy.getDokterDPJP(JenisPelayanan.getJenisPelayanan(jenisPelayanan).getJenis().getKode(),
                        DateUtil.formatTimestampWithTimezone(tglPelayanan, Constant.TIMEZONE_JKT), spesialis,
                        entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getPropinsi")
    public ResponseSts<List<VClaimMappingDto>> getPropinsi(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getPropinsi(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getKabupaten")
    public ResponseSts<List<VClaimMappingDto>> getKabupaten(@RequestParam("kodePropinsi") String kodePropinsi,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getKabupaten(kodePropinsi, entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getKecamatan")
    public ResponseSts<List<VClaimMappingDto>> getKecamatan(@RequestParam("kodeKabupaten") String kodeKabupaten,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getKecamatan(kodeKabupaten, entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getAsalFaskes/{providerCode}")
    public ResponseSts<Faskes> getAsalFaskes(@PathVariable("providerCode") String providerCode,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        ResponseSts<List<VClaimMappingDto>> responseFaskes2 = ResponseSts.onFail("Data tidak ditemukan");
        try {
                responseFaskes2 = getFaskes(providerCode, Faskes.FASKES_2, entityCode);
        } catch (Exception e) {
                //ignore
        }
        if (responseFaskes2.isSuccess()) {
            return ResponseSts.onSuccess(Faskes.FASKES_2);
        }
        return ResponseSts.onSuccess(Faskes.FASKES_1);

    }

    @GetMapping("/getProcedure")
    public ResponseSts<List<VClaimMappingDto>> getProcedure(@RequestParam("procedure") String paramProcedure,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getProcedure(paramProcedure, entityCode));
        return ResponseSts.onSuccess(response.get("procedure"));
    }

    @GetMapping("/getKelasRawat")
    public ResponseSts<List<VClaimMappingDto>> getKelasRawat(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getKelasRawat(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getDokter")
    public ResponseSts<List<VClaimMappingDto>> getDokter(@RequestParam("namaDokter") String namaDokter,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getDokter(namaDokter, entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getCaraKeluar")
    public ResponseSts<List<VClaimMappingDto>> getCaraKeluar(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getCaraKeluar(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getDiagnosaPRB")
    public ResponseSts<List<VClaimMappingDto>> getDiagnosaPRB(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getDiagnosaPRB(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getObatPRB")
    public ResponseSts<List<VClaimMappingDto>> getObatPRB(@RequestParam("namaObat") String namaObat,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getObatPRB(namaObat, entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getPascaPulang")
    public ResponseSts<List<VClaimMappingDto>> getPascaPulang(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getPascaPulang(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getRuangRawat")
    public ResponseSts<List<VClaimMappingDto>> getRuangRawat(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getRuangRawat(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }

    @GetMapping("/getSpesialistik")
    public ResponseSts<List<VClaimMappingDto>> getSpesialistik(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        Map<String, List<VClaimMappingDto>> response = VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getSpesialistik(entityCode));
        return ResponseSts.onSuccess(response.get(LIST));
    }
}
