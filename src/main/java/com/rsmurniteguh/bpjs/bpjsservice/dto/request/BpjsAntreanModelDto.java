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
public class BpjsAntreanModelDto {
    private String kodeBooking;
    private String jenisPasien;
    private String nomorKartu;
    private String nik;
    private String noHp;
    private String kodePoli;
    private String namaPoli;
    private Long pasienBaru;
    private String norm;
    private String tanggalPeriksa;
    private Long kodeDokter;
    private String namaDokter;
    private String jamPraktek;
    private Long jenisKunjungan;
    private String nomorReferensi;
    private String nomorAntrean;
    private Long angkaAntrean;
    private Long estimasiDilayani;
    private Long sisaKuotaJkn;
    private Long kuotaJkn;
    private Long sisakuotaNonJkn;
    private Long kuotaNonJkn;
    private String keterangan;
}
