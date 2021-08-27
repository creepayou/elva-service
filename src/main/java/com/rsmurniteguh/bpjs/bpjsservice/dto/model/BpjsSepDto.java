package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;

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
    private KelasRawat kelasRawat;
    private String noSep;
    private String penjamin;
    private Peserta peserta;
    private Informasi informasi;
    private String poli;
    private String poliEksekutif;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp tglSep;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class Peserta{
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
