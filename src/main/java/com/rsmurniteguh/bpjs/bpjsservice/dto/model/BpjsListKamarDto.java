package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsListKamarDto{
	private List<BpjsListKamarDto> list;
	private Timestamp lastupdate;
	private Long  tersediapriawanita;
    private Long  kapasitas;
    private String  namaruang;
    private String  namakelas;
    private String  koderuang;
    private Long  tersedia;
    private Long rownumber;
    private Long  tersediawanita;
    private Long tersediapria;
    private String kodekelas;
}
