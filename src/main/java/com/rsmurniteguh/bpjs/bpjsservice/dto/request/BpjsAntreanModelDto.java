package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

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
public class BpjsAntreanModelDto {
    private String kodeBooking;
    private String jenisPasien;
    private String nomorKartu;
    private String nik;
    private String noHp;
    private String kodePoli;
    private String namaPoli;
    private boolean pasienBaru;
    private String norm;
    private String tanggalPeriksa;
    private Long kodeDokter;
    private String namaDokter;
    private String jamPraktek;
    private JenisKunjungan jenisKunjungan;
    private String nomorReferensi;
    private String nomorAntrean;
    private Integer angkaAntrean;
    private Long estimasiDilayani;
    private Integer sisaKuotaJkn;
    private Integer kuotaJkn;
    private Integer sisaKuotaNonJkn;
    private Integer kuotaNonJkn;
    private String keterangan;
}
