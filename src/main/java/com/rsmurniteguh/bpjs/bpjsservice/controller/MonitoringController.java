package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusKlaim;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsJaminanDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKlaimDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKunjunganDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController extends BaseController {

	private final VClaimProxy vClaimProxy;
	private final Map<String, String> entityTimeZone;

	public MonitoringController(VClaimProxy vClaimProxy,
			Map<String, String> entityTimeZone) {
		this.vClaimProxy = vClaimProxy;
		this.entityTimeZone = entityTimeZone;
	}

	@GetMapping("/getKunjungan")
	public ResponseSts<List<BpjsKunjunganDto>> getKunjungan(@RequestParam("tanggalSEP") Timestamp tglSEP,
			@RequestParam("jenisPelayanan") JenisPelayanan jnsPelayanan,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getDataKunjungan(
						DateUtil.formatTimestampWithTimezone(tglSEP, entityTimeZone.get(entityCode)),
						jnsPelayanan.getJenis().getKode(), entityCode))
				.get("sep"));

	}

	@GetMapping("/getDataKlaim")
	public ResponseSts<List<BpjsKlaimDto>> getDataKlaim(@RequestParam("tanggalPulang") Timestamp tglPulang,
			@RequestParam("jenisPelayanan") JenisPelayanan jnsPelayanan,
			@RequestParam("statusKlaim") StatusKlaim statusKlaim,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getDataKlaim(
						DateUtil.formatTimestampWithTimezone(tglPulang, entityTimeZone.get(entityCode)),
						jnsPelayanan.getJenis().getKode(), statusKlaim.getStatus().getKode(), entityCode))
				.get("klaim"));

	}

	@GetMapping("/getHistori/{bpjsNo}")
	public ResponseSts<List<BpjsKunjunganDto>> getHistori(@PathVariable("bpjsNo") String noKartu,
			@RequestParam("tglAwal") Timestamp tglAwal, @RequestParam("tglAkhir") Timestamp tglAkhir,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil.handleBpjsResponse(vClaimProxy.getHistoriPelayanan(noKartu,
				DateUtil.formatTimestampWithTimezone(tglAwal, entityTimeZone.get(entityCode)),
				DateUtil.formatTimestampWithTimezone(tglAkhir, entityTimeZone.get(entityCode)), entityCode))
				.get("histori"));

	}

	@GetMapping("/getDataKlaimJasaRaharja")
	public ResponseSts<List<BpjsJaminanDto>> getDataKlaimJasaRaharja(@RequestParam("tglMulai") Timestamp tglMulai,
			@RequestParam("tglAkhir") Timestamp tglAkhir,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getDataKlaimJasaRaharja(
						DateUtil.customFormatTimestampWithTimezone(tglMulai, entityTimeZone.get(entityCode),
								"yyyy-MMdd"),
						DateUtil.customFormatTimestampWithTimezone(tglAkhir, entityTimeZone.get(entityCode),
								"yyyy-MMdd"),
						entityCode))
				.get("klaim"));

	}
}
