package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsAntreanDto {
    private String kodebooking;
    private String jenispasien;
    private String nomorkartu;
    private String nik;
    private String nohp;
    private String kodepoli;
    private String namapoli;
    private Integer pasienbaru;
    private String norm;
    private String tanggalperiksa;
    private Long kodedokter;
    private String namadokter;
    private String jampraktek;
    private Integer jeniskunjungan;
    private String nomorreferensi;
    private String nomorantrean;
    private Integer angkaantrean;
    private Long estimasidilayani;
    private Integer sisakuotajkn;
    private Integer kuotajkn;
    private Integer sisakuotanonjkn;
    private Integer kuotanonjkn;
    private String keterangan;
}
