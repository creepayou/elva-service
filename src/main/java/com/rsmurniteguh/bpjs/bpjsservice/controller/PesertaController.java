package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.BpjsInfoType;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaResponseDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
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
@RequestMapping("/peserta")
public class PesertaController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @GetMapping("/getPesertaByNIK/{nik}")
    public ResponseSts<BpjsPesertaResponseDto> getPesertaByNIK(@PathVariable("nik") String nik,
            @RequestParam(value = "tglSEP", required = false) Timestamp tglSEP,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        if (tglSEP == null)
            tglSEP = new Timestamp(System.currentTimeMillis());
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getPesertaByNik(nik,
                        DateUtil.formatTimestampWithTimezone(tglSEP, Constant.TIMEZONE_JKT), entityCode))
                .get("peserta"));
    }

    @GetMapping("/getPesertaByBpjsNo/{bpjsNo}")
    public ResponseSts<BpjsPesertaResponseDto> getPesertaByBpjsNo(@PathVariable("bpjsNo") String bpjsNo,
            @RequestParam(value = "tglSEP", required = false) Timestamp tglSEP,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        if (tglSEP == null)
            tglSEP = new Timestamp(System.currentTimeMillis());
        return ResponseSts.onSuccess(VClaimResponseUtil
                .handleVClaimResponse(vClaimProxy.getPesertaByNoKartu(bpjsNo,
                        DateUtil.formatTimestampWithTimezone(tglSEP, Constant.TIMEZONE_JKT), entityCode))
                .get("peserta"));
    }
    @GetMapping("/GetBpjsInfobyType")
    public ResponseSts<BpjsPesertaResponseDto> GetBpjsInfobyType(@RequestParam("type") String type,
            @RequestParam("target") String target,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
    	Timestamp tglSEP = new Timestamp(System.currentTimeMillis());
    	BpjsRujukanDto rujukanResponse = null;
    	BpjsPesertaResponseDto peserta = null;
       if(type.equals("CARD")) {
    	   peserta = VClaimResponseUtil
                   .handleVClaimResponse(vClaimProxy.getPesertaByNoKartu(target,
                           DateUtil.formatTimestampWithTimezone(tglSEP, Constant.TIMEZONE_JKT), entityCode))
                   .get("peserta");
       }else if(type.equals("NIK")) {
    	   peserta = (VClaimResponseUtil
                   .handleVClaimResponse(vClaimProxy.getPesertaByNik(target,
                           DateUtil.formatTimestampWithTimezone(tglSEP, Constant.TIMEZONE_JKT), entityCode))
                   .get("peserta"));
       }else if(type.equals("REFERENCE_I")) {
    	   rujukanResponse =VClaimResponseUtil
                   .handleVClaimResponse(vClaimProxy.getRujukanPCareByNoRujukan(target,entityCode));
    	   peserta = rujukanResponse.getRujukan().getPeserta();
    	   return ResponseSts.onSuccess(peserta);
       }else if(type.equals("REFERENCE_II")){
    	   rujukanResponse =VClaimResponseUtil
                   .handleVClaimResponse(vClaimProxy.getRujukanRsByNoRujukan(target,entityCode));
    	   peserta = rujukanResponse.getRujukan().getPeserta();
    	   return ResponseSts.onSuccess(peserta);
       }
       return ResponseSts.onSuccess(peserta);
    }
    
}
