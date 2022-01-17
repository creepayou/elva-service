package com.rsmurniteguh.bpjs.bpjsservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@GetMapping("/bed/read/{param}/1/1000")
	public AplicaresResponse<List<BpjsListKamarDto>> getListKamar(@PathVariable("param") String parameter,@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);

	@GetMapping("/ref/kelas")
	public AplicaresResponse<List<BpjsKodeKamarDto>> getKodeKamar(@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/create/{param}")
	public AplicaresResponse2<Object> createRoom(@PathVariable("param") String parameter,@RequestBody RequestAplicaresDto requestAplicaresDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/update/{param}")
	public AplicaresResponse2<Object> updateRoom(@PathVariable("param") String parameter,@RequestBody RequestAplicaresDto requestAplicaresDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
	@PostMapping("/bed/delete/{param}")
	public AplicaresResponse2<Object> deleteRoom(@PathVariable("param") String parameter,@RequestBody RequestAplicaresDeleteDto requestAplicaresDeleteDto,
			@RequestHeader(Constant.MT_ENTITY_CODE) String entityCode);
	
}
