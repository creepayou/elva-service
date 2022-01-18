package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKodeKamarDto{
	
	private List<BpjsKodeKamarDto> list;
    private String  namakelas;
    private String  kodekelas;
}
