package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanPoliDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAntreanScheduleDoctorDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestJadwalDokterModelDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.AntreanRsProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.VClaimResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/antreanrs")
public class AntreanRsController extends BaseController {

    @Autowired
    private AntreanRsProxy antreanRsProxy;

    @GetMapping("/getReferensiPoli")
    public ResponseSts<List<BpjsAntreanPoliDto>> getReferensiPoli(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiPoli(entityCode)));
    }

    @GetMapping("/getReferensiDokter")
    public ResponseSts<List<BpjsAntreanDoctorDto>> getReferensiDokter(
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiDokter(entityCode)));
    }
    
    @GetMapping("/getReferensiJadwalDokter")
    public ResponseSts<List<BpjsAntreanScheduleDoctorDto>> getReferensiJadwalDokter(
    		@RequestParam(value = "kodePoli", required = true) String kodePoli,
    		@RequestParam(value = "tanggal", required = true) String tanggal,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        return ResponseSts
                .onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.getReferensiJadwalDokter(kodePoli, tanggal, entityCode)));
    }
    
    @PostMapping("/updateJadwalDokter")
    public ResponseSts<Object> updateJadwalDokter(@RequestBody RequestJadwalDokterModelDto requestJadwalDokterModelDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	RequestJadwalDokterDto requestJadwalDokterDto = new RequestJadwalDokterDto();
    	requestJadwalDokterDto.setKodepoli(requestJadwalDokterModelDto.getKodePoli());
    	requestJadwalDokterDto.setKodedokter(requestJadwalDokterModelDto.getKodeDokter());
    	requestJadwalDokterDto.setKodesubspesialis(requestJadwalDokterModelDto.getKodeSubSpesialis());
    	requestJadwalDokterDto.setJadwal(requestJadwalDokterModelDto.getJadwal());
    	return ResponseSts
                .onSuccess(BpjsResponseUtil.handleBpjsResponse(antreanRsProxy.updateJadwalDokter(requestJadwalDokterDto, entityCode)));

    }
    
    
}
