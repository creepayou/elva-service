package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class BpjsAplicaresCrudDto{
	
	private String kodeKelas;
	private String kodeRuang;
	private String namaRuang;
	private Long kapasitas;
	private Long tersedia;
	private Long tersediapria;
	private Long tersediawanita;
	private Long tersediapriawanita;     
}
