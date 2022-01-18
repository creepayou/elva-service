package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestAplicaresDto {
	private String kodekelas;
	private String koderuang;
	private String namaruang;
	private String kapasitas;
	private String tersedia;
	private String tersediapria;
	private String tersediawanita;
	private String tersediapriawanita;  
}
