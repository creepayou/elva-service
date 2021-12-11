package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RencanaKontrolDto{
    private String noSuratKontrol;
    private JenisPelayanan jnsPelayanan;
    private String jnsKontrol;
    private String namaJnsKontrol;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRencanaKontrol;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglTerbitKontrol;
    private String noSepAsalKontrol;
    private String poliAsal;
    private String namaPoliAsal;
    private String poliTujuan;
    private String namaPoliTujuan;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSEP;
    private String kodeDokter;
    private String namaDokter;
    private String noKartu;
    private String nama;
    private String flagKontrol;
    private String kodeDokterPembuat;
    private String namaDokterPembuat;
    private BpjsSepDto sep;
    private ProvUmumDto provUmum;
    private ProvPerujukDto provPerujuk;
    
}
