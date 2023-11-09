package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.sql.Timestamp;
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
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaResponseDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsRujukanDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.VClaimProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;
import com.rsmurniteguh.bpjs.bpjsservice.util.DateUtil;

@RestController
@RequestMapping("/peserta")
public class PesertaController extends BaseController {

	private static final String PESERTA = "peserta";

	private final VClaimProxy vClaimProxy;
	private final Map<String, String> entityTimeZone;

	public PesertaController(VClaimProxy vClaimProxy,
			Map<String, String> entityTimeZone) {
		this.vClaimProxy = vClaimProxy;
		this.entityTimeZone = entityTimeZone;
	}

	@GetMapping("/getPesertaByNIK/{nik}")
	public ResponseSts<BpjsPesertaResponseDto> getPesertaByNIK(@PathVariable("nik") String nik,
			@RequestParam(value = "tglSEP", required = false) Timestamp tglSEP,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		if (tglSEP == null)
			tglSEP = new Timestamp(System.currentTimeMillis());
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getPesertaByNik(nik,
						DateUtil.formatTimestampWithTimezone(tglSEP, entityTimeZone.get(entityCode)), entityCode))
				.get(PESERTA));
	}

	@GetMapping("/getPesertaByBpjsNo/{bpjsNo}")
	public ResponseSts<BpjsPesertaResponseDto> getPesertaByBpjsNo(@PathVariable("bpjsNo") String bpjsNo,
			@RequestParam(value = "tglSEP", required = false) Timestamp tglSEP,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		if (tglSEP == null)
			tglSEP = new Timestamp(System.currentTimeMillis());
		return ResponseSts.onSuccess(BpjsResponseUtil
				.handleBpjsResponse(vClaimProxy.getPesertaByNoKartu(bpjsNo,
						DateUtil.formatTimestampWithTimezone(tglSEP, entityTimeZone.get(entityCode)), entityCode))
				.get(PESERTA));
	}

	@GetMapping("/GetBpjsInfobyType")
	public ResponseSts<BpjsPesertaResponseDto> getBpjsInfobyType(@RequestParam("type") String type,
			@RequestParam("target") String target,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		Timestamp tglSEP = new Timestamp(System.currentTimeMillis());
		BpjsRujukanDto rujukanResponse = null;
		BpjsPesertaResponseDto peserta = null;
		if (type.equals("CARD")) {
			peserta = BpjsResponseUtil
					.handleBpjsResponse(vClaimProxy.getPesertaByNoKartu(target,
							DateUtil.formatTimestampWithTimezone(tglSEP, entityTimeZone.get(entityCode)), entityCode))
					.get(PESERTA);
		} else if (type.equals("NIK")) {
			peserta = (BpjsResponseUtil
					.handleBpjsResponse(vClaimProxy.getPesertaByNik(target,
							DateUtil.formatTimestampWithTimezone(tglSEP, entityTimeZone.get(entityCode)), entityCode))
					.get(PESERTA));
		} else if (type.equals("REFERENCE_I")) {
			rujukanResponse = BpjsResponseUtil
					.handleBpjsResponse(vClaimProxy.getRujukanPCareByNoRujukan(target, entityCode));
			peserta = rujukanResponse.getRujukan().getPeserta();
			return ResponseSts.onSuccess(peserta);
		} else if (type.equals("REFERENCE_II")) {
			rujukanResponse = BpjsResponseUtil
					.handleBpjsResponse(vClaimProxy.getRujukanRsByNoRujukan(target, entityCode));
			peserta = rujukanResponse.getRujukan().getPeserta();
			return ResponseSts.onSuccess(peserta);
		}
		return ResponseSts.onSuccess(peserta);
	}

}
