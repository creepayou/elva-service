package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;

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

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
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
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.VClaimResponse3;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.JsonUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.ObjectUtil;
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
    
    @GetMapping("/getSpesialistik")
    public ResponseSts<List<SpesialistikDto>> getSpesialistik(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol, 
    		@RequestParam("nomor") String nomor, 
    		@RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol, 
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {           
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getSpesialistik(jenisKontrol.getJenis(), nomor,
                    		DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, Constant.TIMEZONE_JKT), entityCode))
                    .get("list"));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
    
    @GetMapping("/getRencanaKontrolByNoSurat")
    public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSurat(@RequestParam("noSuratKontrol") String noSuratKontrol,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {           
            return ResponseSts.onSuccess(VClaimResponseUtil
                    .handleVClaimResponse(vClaimProxy.getRencanaKontrolByNoSurat(noSuratKontrol, entityCode)));
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
    
    @GetMapping("/getRencanaKontrolByNoSep")
    public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSep(@RequestParam("noSep") String noSep,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) {
        try {      
        	RencanaKontrolDto rencanaKontrolDto = new RencanaKontrolDto();
        	VClaimResponse3 responseBpjs = vClaimProxy.getRencanaKontrolByNoSep(noSep, entityCode);
        	if(responseBpjs.getMetaData().getCode().equals(Constant.HTTP_OK)){         
                rencanaKontrolDto.setSep(ObjectUtil.convertObjectToClass(responseBpjs.getResponse(), BpjsSepDto.class));
                rencanaKontrolDto.setProvUmum(responseBpjs.getProvUmum());
                rencanaKontrolDto.setProvPerujuk(responseBpjs.getProvPerujuk());
            } else {
                throw new BpjsServiceException(responseBpjs.getMetaData().getMessage());
            }
        	return ResponseSts.onSuccess(rencanaKontrolDto);
        } catch (BpjsServiceException e){
            return ResponseSts.onFail(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseSts.onFail(e.getMessage());
        }
    }
    
    @PostMapping("/insertRencanaKontrol")
    public ResponseSts<RencanaKontrolCrudDto> insertRencanaKontrol(@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
    		 @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
    	try {
    		return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.insertRencanaKontrol(rqRencanaKontrolDto, entityCode)));
    	} catch (Exception e) {
    		log.error(e.getMessage(), e);
    	    return ResponseSts.onFail(e.getMessage());
    	}
    }
    
    @PutMapping("/updateRencanaKontrol")
    public ResponseSts<RencanaKontrolCrudDto> updateRencanaKontrol(@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
    		 @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
    	try {
    		return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.updateRencanaKontrol(rqRencanaKontrolDto, entityCode)));
    	} catch (Exception e) {
    		log.error(e.getMessage(), e);
    	    return ResponseSts.onFail(e.getMessage());
    	}
    }
    
    private BpjsRequestDto<RequestRencanaKontrolDto> createBpjsRequestRencanaKontrol(RequestRencanaKontrolDto rqRencanaKontrolDto) {
        BpjsRequestDto<RequestRencanaKontrolDto> requestRencanaKontrol = new BpjsRequestDto<>();
        requestRencanaKontrol.getRequest().put("t_suratkontrol", rqRencanaKontrolDto);
        return requestRencanaKontrol;
    }
    
    @DeleteMapping("/deleteRencanaKontrol")
    public ResponseSts<Object> deleteRencanaKontrol(@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
    		 @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
    	try {
    		return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
    				vClaimProxy.deleteRencanaKontrol(createBpjsRequestRencanaKontrol(rqRencanaKontrolDto), entityCode)));
    	} catch (Exception e) {
    		log.error(e.getMessage(), e);
    	    return ResponseSts.onFail(e.getMessage());
    	}
    }
    
    private BpjsRequestDto2<RequestSpriDto> createBpjsRequestSpri(RequestSpriDto rqSpriDto) {
        BpjsRequestDto2<RequestSpriDto> requestSpri = new BpjsRequestDto2<>();
        requestSpri.setRequest(rqSpriDto);
        return requestSpri;
    }
    
    @PostMapping("/insertSpri")
    public ResponseSts<SpriDto> insertSpri(@RequestBody RequestSpriDto rqSpriDto,
    		 @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
    	try {
    		return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.insertSpri(createBpjsRequestSpri(rqSpriDto), entityCode)));
    	} catch (Exception e) {
    		log.error(e.getMessage(), e);
    	    return ResponseSts.onFail(e.getMessage());
    	}
    }
    
    @PutMapping("/updateSpri")
    public ResponseSts<SpriDto> updateSpri(@RequestBody RequestSpriDto rqSpriDto,
    		 @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode){
    	try {
    		System.out.println(JsonUtil.toJsonString(rqSpriDto));
    		return ResponseSts.onSuccess(VClaimResponseUtil.handleVClaimResponse(
                    vClaimProxy.updateSpri(createBpjsRequestSpri(rqSpriDto), entityCode)));
    	} catch (Exception e) {
    		log.error(e.getMessage(), e);
    	    return ResponseSts.onFail(e.getMessage());
    	}
    }
}
