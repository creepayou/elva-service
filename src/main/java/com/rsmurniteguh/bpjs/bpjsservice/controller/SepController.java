package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Lakalantas;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsFingerPrintDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsFingerPrintStatusDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepInternalListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepKllDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepSearchDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepSuplesiDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.VClaimMappingDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestPengajuanSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepInternal;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestUpdateTglPulangDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

@RestController
@RequestMapping("/sep")
public class SepController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    @Autowired
    private Map<String, String> entityTimeZone;

    private static final String KEY_SEP = "sep";

    @GetMapping("/searchSEP/{sepNo}")
    public ResponseSts<BpjsSepSearchDto> searchSEP(@PathVariable("sepNo") String sepNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(vClaimProxy.searchSEP(sepNo, entityCode)));
    }

    private <T> BpjsRequestDto<T> createBpjsRequestSep(T requestSep) {
        BpjsRequestDto<T> bpjsRequestDto = new BpjsRequestDto<>();
        bpjsRequestDto.getRequest().put("t_sep", requestSep);
        return bpjsRequestDto;
    }

    @PostMapping("/insert")
    public ResponseSts<BpjsSepDto> insertSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        String providerCode = bpjsConsumerService.getProviderCodeByEntityCode(entityCode);
        requestSepDto.setPpkPelayanan(providerCode);
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy
                        .insertSEPV2(createBpjsRequestSep(requestSepDto), entityCode))
                .get(KEY_SEP));
    }

    @PutMapping("/update")
    public ResponseSts<String> updateSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponseMessage(vClaimProxy
                        .updateSEPV2(createBpjsRequestSep(requestSepDto), entityCode)));
    }

    @DeleteMapping("/delete")
    public ResponseSts<String> deleteSEP(@RequestBody RequestSepDtoV2 requestSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponseMessage(vClaimProxy
                        .deleteSEPV2(createBpjsRequestSep(requestSepDto), entityCode)));
    }

    @PutMapping("/updateTanggalPulang")
    public ResponseSts<String> updateTanggalPulang(@RequestBody RequestUpdateTglPulangDto requestUpdateTglPulangDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy
                        .updateTglPulangSEPV2(
                                createBpjsRequestSep(requestUpdateTglPulangDto),
                                entityCode)));
    }

    @GetMapping("/getSepInternal/{sepNo}")
    public ResponseSts<BpjsSepInternalListDto> getSepInternal(@PathVariable("sepNo") String sepNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        vClaimProxy.getDataSepInternal(sepNo, entityCode)));

    }

    @DeleteMapping("/deleteSepInternal")
    public ResponseSts<String> getDataSepInternal(@RequestBody RequestSepInternal requestSepInternal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.deleteSepInternal(createBpjsRequestSep(requestSepInternal), entityCode)));

    }

    @PostMapping("/pengajuanSep")
    public ResponseSts<String> pengajuanSep(@RequestBody RequestPengajuanSepDto requestPengajuanSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.pengajuanSEP(createBpjsRequestSep(requestPengajuanSepDto), entityCode)));

    }

    @PostMapping("/approvalSep")
    public ResponseSts<String> approvalSep(@RequestBody RequestPengajuanSepDto requestPengajuanSepDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.approvalSEP(createBpjsRequestSep(requestPengajuanSepDto), entityCode)));

    }

    @GetMapping("/getFingerPrintStatus/{bpjsNo}")
    public ResponseSts<BpjsFingerPrintStatusDto> getFingerPrintStatus(@PathVariable("bpjsNo") String bpjsNo,
            @RequestParam("tglPelayanan") Timestamp tglPelayanan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil
                        .handleBpjsResponse(vClaimProxy.getFingerPrintStatus(bpjsNo,
                                DateUtil.formatTimestampWithTimezone(tglPelayanan,
                                        entityTimeZone.get(entityCode)),
                                entityCode)));
    }

    @GetMapping("/getFingerPrintList")
    public ResponseSts<List<BpjsFingerPrintDto>> getFingerPrintStatus(
            @RequestParam("tglPelayanan") Timestamp tglPelayanan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getFingerPrintList(
                        DateUtil.formatTimestampWithTimezone(tglPelayanan,
                                entityTimeZone.get(entityCode)),
                        entityCode))
                .get("list"));
    }

    @GetMapping("/getSuplesiJasaRaharja/{bpjsNo}")
    public ResponseSts<List<BpjsSepSuplesiDto>> getSuplesiJasaRaharja(@PathVariable("bpjsNo") String bpjsNo,
            @RequestParam("tglPelayanan") Timestamp tglPelayanan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getPotensiSuplesiJasaRaharja(bpjsNo,
                        DateUtil.formatTimestampWithTimezone(tglPelayanan,
                                entityTimeZone.get(entityCode)),
                        entityCode))
                .get("jaminan"));

    }

    @GetMapping("/getDataKecelakaan/{bpjsNo}")
    public ResponseSts<List<BpjsSepKllDto>> getDataKecelakaan(@PathVariable("bpjsNo") String bpjsNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getDataKecelakaanInduk(bpjsNo, entityCode))
                .get("list"));

    }

    @GetMapping("/getLakaLantas")
    public ResponseSts<List<VClaimMappingDto>> getLakaLantas(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        return ResponseSts.onSuccess(loadKLL());

    }

    private List<VClaimMappingDto> loadKLL() {
        List<VClaimMappingDto> vclaimMappingDtoList = new ArrayList<>();
        for (Lakalantas item : Lakalantas.values()) {
            VClaimMappingDto dto = new VClaimMappingDto(item.name(), item.getKll().getNama());
            vclaimMappingDtoList.add(dto);
        }
        return vclaimMappingDtoList;
    }

}
