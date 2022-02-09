package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FilterTanggalRencanaKontrol;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisKontrol;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsSepDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.DataDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpesialistikDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRencanaKontrolDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse3;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.ObjectUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

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

@RestController
@RequestMapping("/rencanakontrol")
public class RencanaKontrolController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getRencanaKontrol")
    public ResponseSts<List<RencanaKontrolDto>> getRencanaKontrol(@RequestParam("tglAwal") Timestamp tglAwal,
            @RequestParam("tglAkhir") Timestamp tglAkhir, @RequestParam("filter") FilterTanggalRencanaKontrol filter,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                vClaimProxy.getRencanaKontrol(DateUtil.formatTimestampWithTimezone(tglAwal, Constant.TIMEZONE_JKT),
                        DateUtil.formatTimestampWithTimezone(tglAkhir, Constant.TIMEZONE_JKT), filter.getFilter(),
                        entityCode))
                .get("list"));
    }

    @GetMapping("/getDataDokter")
    public ResponseSts<List<DataDokterDto>> getDataDokter(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol,
            @RequestParam("poli") String poli, @RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getDataDokter(jenisKontrol.getJenis(), poli,
                        DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, Constant.TIMEZONE_JKT), entityCode))
                .get("list"));
    }

    @GetMapping("/getSpesialistik")
    public ResponseSts<List<SpesialistikDto>> getSpesialistik(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol,
            @RequestParam("nomor") String nomor, @RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getSpesialistik(jenisKontrol.getJenis(), nomor,
                        DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, Constant.TIMEZONE_JKT), entityCode))
                .get("list"));
    }

    @GetMapping("/getRencanaKontrolByNoSurat")
    public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSurat(
            @RequestParam("noSuratKontrol") String noSuratKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getRencanaKontrolByNoSurat(noSuratKontrol, entityCode)));
    }

    @GetMapping("/getRencanaKontrolByNoSep")
    public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSep(@RequestParam("noSep") String noSep,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException, IOException {
        RencanaKontrolDto rencanaKontrolDto = new RencanaKontrolDto();
        VClaimResponse3 responseBpjs = vClaimProxy.getRencanaKontrolByNoSep(noSep, entityCode);
        if (responseBpjs.getMetaData().getCode().equals(Constant.HTTP_OK)) {
            rencanaKontrolDto.setSep(ObjectUtil.convertObjectToClass(responseBpjs.getResponse(), BpjsSepDto.class));
            rencanaKontrolDto.setProvUmum(responseBpjs.getProvUmum());
            rencanaKontrolDto.setProvPerujuk(responseBpjs.getProvPerujuk());
        } else {
            throw new BusinessException(responseBpjs.getMetaData().getMessage());
        }
        return ResponseSts.onSuccess(rencanaKontrolDto);
    }

    @PostMapping("/insert")
    public ResponseSts<RencanaKontrolCrudDto> insertRencanaKontrol(
            @RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.insertRencanaKontrol(createRencanaKontrolRequest(rqRencanaKontrolDto),
                        entityCode)));

    }

    @PutMapping("/update")
    public ResponseSts<RencanaKontrolCrudDto> updateRencanaKontrol(
            @RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.updateRencanaKontrol(createRencanaKontrolRequest(rqRencanaKontrolDto),
                        entityCode)));

    }

    private BpjsRequestDto<RequestRencanaKontrolDto> createBpjsRequestRencanaKontrol(
            RequestRencanaKontrolDto rqRencanaKontrolDto) {
        BpjsRequestDto<RequestRencanaKontrolDto> requestRencanaKontrol = new BpjsRequestDto<>();
        requestRencanaKontrol.getRequest().put("t_suratkontrol", rqRencanaKontrolDto);
        return requestRencanaKontrol;
    }

    @DeleteMapping("/delete")
    public ResponseSts<Object> deleteRencanaKontrol(@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(vClaimProxy
                .deleteRencanaKontrol(createBpjsRequestRencanaKontrol(rqRencanaKontrolDto), entityCode)));

    }

    private <T> BpjsRequestDto2<T> createRencanaKontrolRequest(T request) {
        BpjsRequestDto2<T> bpjsRequest = new BpjsRequestDto2<>();
        bpjsRequest.setRequest(request);
        return bpjsRequest;
    }

    @PostMapping("/insertSPRI")
    public ResponseSts<SpriDto> insertSPRI(@RequestBody RequestSpriDto rqSpriDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.insertSpri(createRencanaKontrolRequest(rqSpriDto), entityCode)));

    }

    @PutMapping("/updateSPRI")
    public ResponseSts<SpriDto> updateSPRI(@RequestBody RequestSpriDto rqSpriDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.updateSpri(createRencanaKontrolRequest(rqSpriDto), entityCode)));

    }
}