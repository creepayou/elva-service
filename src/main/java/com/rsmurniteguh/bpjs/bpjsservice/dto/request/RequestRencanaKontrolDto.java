package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestRencanaKontrolDto {
	private String noSuratKontrol;
    private String noSEP;
    private String kodeDokter;
    private String poliKontrol;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglRencanaKontrol;
    private String user;
}
