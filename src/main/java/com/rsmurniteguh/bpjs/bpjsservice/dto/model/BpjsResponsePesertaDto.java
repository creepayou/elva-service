package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsResponsePesertaDto {
    private Asuransi cob;
    private DetailPeserta hakKelas;
    private Informasi informasi;
    private DetailPeserta jenisPeserta;
    private MR mr;
    private String nama;
    private String nik;
    private String noKartu;
    private String pisa;
    private Provider provUmum;
    private String sex;
    private DetailPeserta statusPeserta;
    @JsonDeserialize(using= CustomJsonDateDeserializer.class)
    private Timestamp tglCetakKartu;
    @JsonDeserialize(using= CustomJsonDateDeserializer.class)
    private Timestamp tglLahir;
    @JsonDeserialize(using= CustomJsonDateDeserializer.class)
    private Timestamp tglTAT;
    @JsonDeserialize(using= CustomJsonDateDeserializer.class)
    private Timestamp tglTMT;
    private Umur umur;

    public void setNoMr(String noMR){
        if(this.mr == null) this.mr = new MR();
        this.mr.setNoMR(noMR);
    }

    @Data
    @Accessors(chain = true)
    @JsonInclude(value = Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Asuransi {
        private Indikator cob;
        private String nmAsuransi;
        private String noAsuransi;
        @JsonDeserialize(using= CustomJsonDateDeserializer.class)
        private Timestamp tglTAT;
        @JsonDeserialize(using= CustomJsonDateDeserializer.class)
        private Timestamp tglTMT; 
    }
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class Informasi {
    @JsonAlias({"Dinsos"})
    private String dinSos;
    private String noSKTM;
    private String prolanisPRB;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class DetailPeserta {
    private String keterangan;
    private String kode;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class MR {
    private String noMR;
    private String noTelepon;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class Provider {
    private String kdProvider;
    private String nmProvider;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
final class Umur {
    private String umurSaatPelayanan;
    private String umurSekarang;
}