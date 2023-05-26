package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Lakalantas;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaResponseDto.Informasi;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsSepDto {
    private String catatan;
    private String diagnosa;
    private JenisPelayanan jnsPelayanan;
    private KelasRawat kelasRawat;
    private String noMr;
    @JsonAlias("noSEP")
    private String noSep;
    private String penjamin;
    private BpjsPesertaDto peserta;
    private Informasi informasi;
    private String poli;
    private Indikator poliEksekutif;
    @JsonAlias("tglSEP")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSep;
    @JsonAlias("tglPlgSEP")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglPlgSep;
    private String noRujukan;
    private Lakalantas kdStatusKecelakaan;
    private String nmstatusKecelakaan;
    private LokasiKejadianDto lokasiKejadian;
    private DokterDPJPDto dpjp;
    private KelasRawatDto klsRawat;
    private DokterDPJPDto kontrol;
    private Indikator cob;
    private Indikator katarak;
    private ProvUmumDto provUmum;
    private ProvPerujukDto provPerujuk;

}