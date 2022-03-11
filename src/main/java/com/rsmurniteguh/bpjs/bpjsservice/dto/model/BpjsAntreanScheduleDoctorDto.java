package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsAntreanScheduleDoctorDto {
    @JsonAlias("kodesubspesialis")
    private String kodeSubSpesialis;
    @JsonAlias("hari")
    private Long hari;
    @JsonAlias("kapasitaspasien")
    private Long kapasitasPasien;
    @JsonAlias("libur")
    private Long libur;
    @JsonAlias("namahari")
    private String namaHari;
    @JsonAlias("jadwal")
    private String jadwal;
    @JsonAlias("namasubspesialis")
    private String namaSubSpesialis;
    @JsonAlias("namadokter")
    private String namaDokter;
    @JsonAlias("kodepoli")
    private String KodePoli;
    @JsonAlias("namapoli")
    private String namaPoli;
    @JsonAlias("kodedokter")
    private Long kodeDokter;
}
