package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class BpjsPesertaDto{
    private String asuransi;
    private String hakKelas;
    private String jnsPeserta;
    private String kelamin;
    private String nama;
    private String noKartu;
    private String noMr;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp tglLahir;
}
