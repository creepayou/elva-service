package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {
    
    @AllArgsConstructor
    public enum JenisPelayanan{
        RAWAT_INAP("1"),
        RAWAT_JALAN("2");

        @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum TipeRujukan{
        PENUH("1"),
        PARTIAL("2"),
        RUJUK_BALIK("3");

        @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum KelasRawat{
        KELAS_I("1"),
        KELAS_II("2"),
        KELAS_III("3");

        @Getter private String kelas;
    }

    @AllArgsConstructor
    public enum Faskes{
        FASKES_1("1"),
        FASKES_2("2");

        @Getter private String jenis;
    }

    @AllArgsConstructor
    public enum Indikator{
        TIDAK("0"),
        YA("1");

        @Getter private String ind;
    }

    @AllArgsConstructor
    public enum StatusKlaim{
        PROSES("Proses Verifikasi"),
        PENDING("Pending Verifikasi"),
        KLAIM("Klaim");

        @Getter private String status;
    }

    @AllArgsConstructor
    public enum Penjamin{
        JASA_RAHARJA("1"),
        BPJS_TK("2"),
        TASPEN("3"),
        ASABRI("4");

        @Getter private String jenis;
    }
}
