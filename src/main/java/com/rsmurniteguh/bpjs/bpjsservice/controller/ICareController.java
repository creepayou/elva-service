package com.rsmurniteguh.bpjs.bpjsservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.base.controller.BaseController;
import com.rsmurniteguh.bpjs.bpjsservice.base.model.ResponseSts;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.ValidateICareDto;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;
import com.rsmurniteguh.bpjs.bpjsservice.proxy.IHSProxy;
import com.rsmurniteguh.bpjs.bpjsservice.util.BpjsResponseUtil;

@RestController
@RequestMapping("/iCare")
public class ICareController extends BaseController {

	@Autowired
	private IHSProxy ihsProxy;

	@PostMapping("/validate")
	public ResponseSts<String> validate(@RequestBody ValidateICareDto validateICareDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode) throws BusinessException {
		Map<String, String> resMap = BpjsResponseUtil.handleBpjsResponse(ihsProxy.validate(validateICareDto, entityCode));
		return ResponseSts
				.onSuccess(resMap.get("url"));
	}

}
