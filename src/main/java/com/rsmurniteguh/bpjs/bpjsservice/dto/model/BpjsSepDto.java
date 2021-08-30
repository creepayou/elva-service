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
public class BpjsSepDto {
    private String catatan;
    private String diagnosa;
    private String jnsPelayanan;
    private String kelasRawat;
    private String noSep;
    private String penjamin;
    private BpjsPesertaDto peserta;
    private Informasi informasi;
    private String poli;
    private String poliEksekutif;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp tglSep;
}