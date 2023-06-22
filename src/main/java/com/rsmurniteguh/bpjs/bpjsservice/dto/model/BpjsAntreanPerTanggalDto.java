package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisKunjungan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsAntreanPerTanggalDto {
    @JsonAlias("kodebooking")
    private String kodeBooking;
    @JsonAlias("tanggal")
    private String tanggal;
    @JsonAlias("kodepoli")
    private String kodePoli;
    @JsonAlias("kodedokter")
    private Long kodeDokter;
    @JsonAlias("jampraktek")
    private String jamPraktek;
    @JsonAlias("nik")
    private String nik;
    @JsonAlias("nokapst")
    private String nokapst;
    @JsonAlias("nohp")
    private String noHp;
    @JsonAlias("norekammedis")
    private String noRekammedis;
    @JsonAlias("jeniskunjungan")
    private JenisKunjungan jenisKunjungan;
    @JsonAlias("nomorreferensi")
    private String nomorReferensi;
    @JsonAlias("sumberdata")
    private String sumberData;
    @JsonAlias("ispeserta")
    private boolean isPeserta;
    @JsonAlias("noantrean")
    private String noAntrean;
    @JsonAlias("estimasidilayani")
    private Timestamp estimasiDilayani;
    @JsonAlias("createdtime")
    private Timestamp createdTime;
    @JsonAlias("status")
    private String status;
}