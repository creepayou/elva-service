package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.BpjsResponse2;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

@RestController
@RequestMapping("/rencanakontrol")
public class RencanaKontrolController extends BaseController {

	private final VClaimProxy vClaimProxy;
	private final Map<String, String> entityTimeZone;

	public RencanaKontrolController(VClaimProxy vClaimProxy,
			Map<String, String> entityTimeZone) {
		this.vClaimProxy = vClaimProxy;
		this.entityTimeZone = entityTimeZone;
	}

	@GetMapping("/getRencanaKontrol")
	public ResponseSts<List<RencanaKontrolDto>> getRencanaKontrol(@RequestParam("tglAwal") Timestamp tglAwal,
			@RequestParam("tglAkhir") Timestamp tglAkhir, @RequestParam("filter") FilterTanggalRencanaKontrol filter,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(
				vClaimProxy.getRencanaKontrol(
						DateUtil.formatTimestampWithTimezone(tglAwal, entityTimeZone.get(entityCode)),
						DateUtil.formatTimestampWithTimezone(tglAkhir, entityTimeZone.get(entityCode)),
						filter.getFilter(),
						entityCode))
				.get("list"));
	}

	@GetMapping("/getDataDokter")
	public ResponseSts<List<DataDokterDto>> getDataDokter(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol,
			@RequestParam("poli") String poli, @RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getDataDokter(jenisKontrol.getJenis(), poli,
						DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, entityTimeZone.get(entityCode)),
						entityCode))
				.get("list"));
	}

	@GetMapping("/getSpesialistik")
	public ResponseSts<List<SpesialistikDto>> getSpesialistik(@RequestParam("jenisKontrol") JenisKontrol jenisKontrol,
			@RequestParam("nomor") String nomor, @RequestParam("tglRencanaKontrol") Timestamp tglRencanaKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getSpesialistik(jenisKontrol.getJenis(), nomor,
						DateUtil.formatTimestampWithTimezone(tglRencanaKontrol, entityTimeZone.get(entityCode)),
						entityCode))
				.get("list"));
	}

	@GetMapping("/getRencanaKontrolByNoSurat")
	public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSurat(
			@RequestParam("noSuratKontrol") String noSuratKontrol,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getRencanaKontrolByNoSurat(noSuratKontrol, entityCode)));
	}

	@GetMapping("/getRencanaKontrolByNoSep")
	public ResponseSts<RencanaKontrolDto> getRencanaKontrolByNoSep(@RequestParam("noSep") String noSep,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		RencanaKontrolDto rencanaKontrolDto = new RencanaKontrolDto();
		BpjsResponse2<BpjsSepDto> responseBpjs = vClaimProxy.getRencanaKontrolByNoSep(noSep, entityCode);
		if (responseBpjs.getMetaData().getCode().equals(Constant.METADATA_OK_200)) {
			rencanaKontrolDto.setSep(responseBpjs.getResponse());
		} else {
			throw new BusinessException(responseBpjs.getMetaData().getMessage());
		}
		return ResponseSts.onSuccess(rencanaKontrolDto);
	}

	@PostMapping("/insert")
	public ResponseSts<RencanaKontrolCrudDto> insertRencanaKontrol(
			@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.insertRencanaKontrol(createRencanaKontrolRequest(rqRencanaKontrolDto),
						entityCode)));

	}

	@PutMapping("/update")
	public ResponseSts<RencanaKontrolCrudDto> updateRencanaKontrol(
			@RequestBody RequestRencanaKontrolDto rqRencanaKontrolDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.updateRencanaKontrol(createRencanaKontrolRequest(rqRencanaKontrolDto),
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
		return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(vClaimProxy
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
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.insertSpri(createRencanaKontrolRequest(rqSpriDto), entityCode)));

	}

	@PutMapping("/updateSPRI")
	public ResponseSts<SpriDto> updateSPRI(@RequestBody RequestSpriDto rqSpriDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.updateSpri(createRencanaKontrolRequest(rqSpriDto), entityCode)));

	}
}
