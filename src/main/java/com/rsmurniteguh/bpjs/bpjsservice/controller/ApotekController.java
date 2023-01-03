package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsDphoDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.ApotekProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

@RestController
@RequestMapping("/apotek")
public class ApotekController extends BaseController {

	@Autowired
	private ApotekProxy apotekProxy;

	@GetMapping("/getDpho")
	public ResponseSts<List<BpjsDphoDto>> getDpho(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode)
			throws BusinessException {
		Map<String, List<BpjsDphoDto>> map = BpjsResponseUtil.handleBpjsResponse(apotekProxy.getDpho(entityCode));
		List<BpjsDphoDto> result = map.get("list");
		return ResponseSts.onSuccess(result);
	}
}
