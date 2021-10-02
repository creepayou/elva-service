package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsPesertaDto{
    private String asuransi;
    private String hakKelas;
    private String jnsPeserta;
    private String kelamin;
    private String nama;
    private String noKartu;
    private String noMr;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglLahir;
}
