package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {
    
    @AllArgsConstructor
    public enum JenisPelayanan{
        RAWAT_INAP("1"),
        RAWAT_JALAN("2");

        @JsonValue @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum TipeRujukan{
        PENUH("1"),
        PARTIAL("2"),
        RUJUK_BALIK("3");

        @JsonValue @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum KelasRawat{
        KELAS_I("1"),
        KELAS_II("2"),
        KELAS_III("3");

        @JsonValue @Getter private String kelas;
    }

    @AllArgsConstructor
    public enum Faskes{
        FASKES_1("1"),
        FASKES_2("2");

        @JsonValue @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum Indikator{
        TIDAK("0"),
        YA("1");

        @JsonValue @Getter private String ind;
    }

    @AllArgsConstructor
    public enum StatusKlaim{
        PROSES_VERIFIKASI("1"),
        PENDING_VERIFIKASI("2"),
        KLAIM("3");

        @JsonValue @Getter private String status;
    }

    @AllArgsConstructor
    public enum Penjamin{
        JASA_RAHARJA("1"),
        BPJS_TK("2"),
        TASPEN("3"),
        ASABRI("4");

        @JsonValue @Getter private String jenis;
    }
}
