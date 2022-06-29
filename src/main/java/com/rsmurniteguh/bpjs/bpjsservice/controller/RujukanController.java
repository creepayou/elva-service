package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsConsumerDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisRujukan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanListDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.RujukanKhususDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SaranaDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.SpesialistikRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestRujukanKhususDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.service.BpjsConsumerService;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

@RestController
@RequestMapping("/rujukan")
public class RujukanController extends BaseController {

    @Autowired
    private VClaimProxy vClaimProxy;

    @Autowired
    private BpjsConsumerService bpjsConsumerService;

    private static final String KEY_RUJUKAN = "rujukan";

    @GetMapping("/getRujukanByNoRujukan/{jenisRujukan}/{noRujukan}")
    public ResponseSts<BpjsRujukanDto> getRujukanByNoRujukan(@PathVariable("jenisRujukan") JenisRujukan jenisRujukan,
            @PathVariable("noRujukan") String noRujukan, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        if (jenisRujukan.equals(JenisRujukan.PCARE)) {
            return ResponseSts.onSuccess(BpjsResponseUtil
                    .handleBpjsResponse(vClaimProxy.getRujukanPCareByNoRujukan(noRujukan, entityCode)));
        }
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getRujukanRsByNoRujukan(noRujukan, entityCode)));
    }

    @GetMapping("/getRujukanByBpjsNo/{jenisRujukan}/{bpjsNo}")
    public ResponseSts<BpjsRujukanDto> getRujukanByBpjsNo(@PathVariable("jenisRujukan") JenisRujukan jenisRujukan,
            @PathVariable("bpjsNo") String bpjsNo, @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
            throws BusinessException {
        if (jenisRujukan.equals(JenisRujukan.PCARE)) {
            return ResponseSts.onSuccess(BpjsResponseUtil
                    .handleBpjsResponse(vClaimProxy.getRujukanPCareByNoKartu(bpjsNo, entityCode)));
        }
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(vClaimProxy.getRujukanRsByNoKartu(bpjsNo, entityCode)));
    }

    @GetMapping("/getRujukanListByBpjsNo/{jenisRujukan}/{bpjsNo}")
    public ResponseSts<BpjsRujukanListDto> getRujukanListByBpjsNo(
            @PathVariable("jenisRujukan") JenisRujukan jenisRujukan, @PathVariable("bpjsNo") String bpjsNo,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        if (jenisRujukan.equals(JenisRujukan.PCARE)) {
            return ResponseSts.onSuccess(BpjsResponseUtil
                    .handleBpjsResponse(vClaimProxy.getRujukanPCareListByNoKartu(bpjsNo, entityCode)));
        }
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(vClaimProxy.getRujukanRsListByNoKartu(bpjsNo, entityCode)));
    }

    private BpjsRequestDto<RequestRujukanDto> createBpjsRequestRujukan(RequestRujukanDto requestRujukanDto) {
        BpjsRequestDto<RequestRujukanDto> requestRujukan = new BpjsRequestDto<>();
        requestRujukan.getRequest().put("t_rujukan", requestRujukanDto);
        return requestRujukan;
    }

    @PostMapping("/insert")
    public ResponseSts<BpjsRujukanDto> insertRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        BpjsConsumerDto bpjsConsumerDto = bpjsConsumerService.getBpjsConsumerByEntityCode(entityCode);
        if (!StringUtils.hasText(requestRujukanDto.getPpkDirujuk())) {
            requestRujukanDto.setPpkDirujuk(bpjsConsumerDto.getProviderCode());
        }

        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.insertRujukanV2(createBpjsRequestRujukan(requestRujukanDto), entityCode)));
    }

    @PutMapping("/update")
    public ResponseSts<Object> updateRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(
                        vClaimProxy.updateRujukanV2(createBpjsRequestRujukan(requestRujukanDto), entityCode)));
    }

    @DeleteMapping("/delete")
    public ResponseSts<String> deleteRujukan(@RequestBody RequestRujukanDto requestRujukanDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(
                        vClaimProxy.deleteRujukan(createBpjsRequestRujukan(requestRujukanDto), entityCode)));
    }

    @GetMapping("/getSarana")
    public ResponseSts<List<SaranaDto>> getSarana(@RequestParam("ppkRujukan") String ppkRujukan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        vClaimProxy.getSarana(ppkRujukan, entityCode)).get("list"));
    }

    @GetMapping("/getSpesialistikRujukan")
    public ResponseSts<List<SpesialistikRujukanDto>> getSpesialistikRujukan(
            @RequestParam("ppkRujukan") String ppkRujukan,
            @RequestParam("tglRujukan") Timestamp tglRujukan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        vClaimProxy.getSpesialistikRujukan(ppkRujukan,
                                DateUtil.formatTimestampWithTimezone(tglRujukan, Constant.TIMEZONE_JKT),
                                entityCode))
                        .get("list"));
    }

    @GetMapping("/getRujukanKhusus")
    public ResponseSts<List<RujukanKhususDto>> getRujukanKhusus(@RequestParam("bulan") String bulan,
            @RequestParam("tahun") String tahun,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(
                BpjsResponseUtil.handleBpjsResponse(
                        vClaimProxy.getRujukanKhusus(bulan, tahun, entityCode)).get(KEY_RUJUKAN));
    }

    @PostMapping("/insertRujukanKhusus")
    public ResponseSts<RujukanKhususDto> insertRujukanKhusus(@RequestBody RequestRujukanKhususDto rqRujukanKhususDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.insertRujukanKhusus(rqRujukanKhususDto, entityCode)).get(KEY_RUJUKAN));
    }

    private BpjsRequestDto<RequestRujukanKhususDto> createBpjsRequestRencanaKontrol(
            RequestRujukanKhususDto rqRujukanKhususDto) {
        BpjsRequestDto<RequestRujukanKhususDto> requestRujukanKhusus = new BpjsRequestDto<>();
        requestRujukanKhusus.getRequest().put("t_rujukan", rqRujukanKhususDto);
        return requestRujukanKhusus;
    }

    @DeleteMapping("/deleteRujukanKhusus")
    public ResponseSts<String> deleteRujukanKhusus(@RequestBody RequestRujukanKhususDto rqRujukanKhususDto,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
                vClaimProxy.deleteRujukanKhusus(createBpjsRequestRencanaKontrol(rqRujukanKhususDto), entityCode))
                .get(KEY_RUJUKAN));
    }

    @GetMapping("/getRujukanKeluarByNoRujukan/{noRujukan}")
    public ResponseSts<Object> getRujukanKeluarByNoRujukan(@PathVariable String noRujukan,
            @RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
        return ResponseSts.onSuccess(BpjsResponseUtil
                .handleBpjsResponse(vClaimProxy.getRujukanKeluarByNoRujukan(noRujukan, entityCode)).get(KEY_RUJUKAN));
    }
}
