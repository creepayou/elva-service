package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.config.BpjsRequestConfig;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsAplicaresCrudDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKodeKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsListKamarDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.BpjsRequestDto2;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDeleteDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestAplicaresDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSpriDto;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.AplicaresResponse;
import com.rsmurniteguh.bpjs.bpjsservice.dto.response.AplicaresResponse2;

@FeignClient(name = "aplicares", url = "${proxy.aplicaresrest.host}", configuration = BpjsRequestConfig.class)
public interface AplicaresProxy {

	@GetMapping("/bed/read/0038R091/1/1000")
	public AplicaresResponse<List<BpjsListKamarDto>> getListKamar(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/ref/kelas")
	public AplicaresResponse<List<BpjsKodeKamarDto>> getKodeKamar(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/create/0038R091")
	public AplicaresResponse2<Object> createRoom(@RequestBody RequestAplicaresDto requestAplicaresDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/update/0038R091")
	public AplicaresResponse2<Object> updateRoom(@RequestBody RequestAplicaresDto requestAplicaresDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/delete/0038R091")
	public AplicaresResponse2<Object> deleteRoom(@RequestBody RequestAplicaresDeleteDto requestAplicaresDeleteDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
}
