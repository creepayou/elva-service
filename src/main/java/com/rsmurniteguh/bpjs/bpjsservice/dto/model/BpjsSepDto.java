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
    private LokasiKejadian lokasiKejadian;
    private DokterDPJP dpjp;
    private BpjsKelasRawatDto klsRawat;
    private DokterDPJP kontrol;
    private Indikator cob;
    private Indikator katarak;

    @Data
    @Accessors(chain = true)
    public static class LokasiKejadian{
        private String kdProp;
        private String kdKab;
        private String kdKec;
        private String ketKejadian;
        private String lokasi;
        @JsonDeserialize(using = CustomJsonDateDeserializer.class)
        private Timestamp tglKejadian;
    }

    @Data
    @Accessors(chain = true)
    public static class DokterDPJP{
        @JsonAlias("kdDPJP")
        private String kdDokter;
        @JsonAlias("nmDPJP")
        private String nmDokter;
        private String noSurat;
    }

}