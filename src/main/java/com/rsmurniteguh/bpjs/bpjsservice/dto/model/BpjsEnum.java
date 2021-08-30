package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {

    @AllArgsConstructor
    public enum JenisPelayanan {
        RAWAT_INAP(new VClaimMappingDto().setKode("1").setNama("R.Inap")),
        RAWAT_JALAN(new VClaimMappingDto().setKode("2").setNama("R.Jalan"));

        @JsonValue
        @Getter
        private VClaimMappingDto jenis;

        @JsonCreator
        public static JenisPelayanan fromValue(String value){
            return getJenisPelayanan(value);
        }

        @JsonIgnore
        private static final Map<String, JenisPelayanan> BY_JENIS = new HashMap<>();

        static {
            for (JenisPelayanan jp : values()) {
                BY_JENIS.put(jp.getJenis().getKode(), jp);
                BY_JENIS.put(jp.getJenis().getNama(), jp);
            }
        }

        @JsonIgnore
        public static JenisPelayanan getJenisPelayanan(String jenis) {
            if (BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new IllegalStateException("Jenis Pelayanan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum TipeRujukan {
        PENUH("1"), PARTIAL("2"), RUJUK_BALIK("3");

        @JsonValue
        @Getter
        private String tipe;

        @JsonIgnore
        private static final Map<String, TipeRujukan> BY_TIPE = new HashMap<>();

        static {
            for (TipeRujukan tr : values()) {
                BY_TIPE.put(tr.getTipe(), tr);
            }
        }

        @JsonIgnore
        public static TipeRujukan getTipeRujukan(String tipe) {
            if (BY_TIPE.containsKey(tipe))
                return BY_TIPE.get(tipe);
            else
                throw new IllegalStateException("Tipe Rujukan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum KelasRawat {
        KELAS_I(new VClaimMappingDto().setKode("1").setNama("Kelas 1")), 
        KELAS_II(new VClaimMappingDto().setKode("2").setNama("Kelas 2")), 
        KELAS_III(new VClaimMappingDto().setKode("3").setNama("Kelas 3")),
        UNKNOWN(new VClaimMappingDto().setKode("-").setNama("Tidak Ada"));

        @JsonValue
        @Getter
        private VClaimMappingDto kelas;

        @JsonIgnore
        private static final Map<String, KelasRawat> BY_KELAS = new HashMap<>();

        static {
            for (KelasRawat kr : values()) {
                BY_KELAS.put(kr.getKelas().getKode(), kr);
                BY_KELAS.put(kr.getKelas().getNama(), kr);
            }
        }

        @JsonCreator
        public static KelasRawat fromValue(String value){
            return getKelasRawat(value);
        }

        @JsonIgnore
        public static KelasRawat getKelasRawat(String kelas) {
            if(BY_KELAS.containsKey(kelas))
                return BY_KELAS.get(kelas);
            else
                throw new IllegalStateException("Kelas Rawat tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Faskes {
        FASKES_1("1"), FASKES_2("2");

        @JsonValue
        @Getter
        private String jenis;

        @JsonIgnore
        private static final Map<String, Faskes> BY_JENIS = new HashMap<>();

        static {
            for (Faskes fk : values()) {
                BY_JENIS.put(fk.getJenis(), fk);
            }
        }

        @JsonIgnore
        public static Faskes getJenisFaskes(String jenis) {
            if(BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new IllegalStateException("Faskes tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Indikator {
        TIDAK("0"), YA("1");

        @JsonValue
        @Getter
        private String ind;

        @JsonIgnore
        private static final Map<String, Indikator> BY_IND = new HashMap<>();

        static {
            for (Indikator ind : values()) {
                BY_IND.put(ind.getInd(), ind);
            }
        }

        @JsonIgnore
        public static Indikator getIndikator(String ind) {
            if(BY_IND.containsKey(ind))
                return BY_IND.get(ind);
            else
                throw new IllegalStateException("Jenis Pelayanan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum StatusKlaim {
        PROSES_VERIFIKASI("1"), PENDING_VERIFIKASI("2"), KLAIM("3");

        @JsonValue
        @Getter
        private String status;

        @JsonIgnore
        private static final Map<String, StatusKlaim> BY_STATUS = new HashMap<>();

        static {
            for (StatusKlaim sts : values()) {
                BY_STATUS.put(sts.getStatus(), sts);
            }
        }

        @JsonIgnore
        public static StatusKlaim getStatusKlaim(String status) {
            if(BY_STATUS.containsKey(status))
                return BY_STATUS.get(status);
            else
                throw new IllegalStateException("Status Klaim tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Penjamin {
        JASA_RAHARJA("1"), BPJS_TK("2"), TASPEN("3"), ASABRI("4");

        @JsonValue
        @Getter
        private String jenis;

        @JsonIgnore
        private static final Map<String, Penjamin> BY_JENIS = new HashMap<>();

        static {
            for (Penjamin pj : values()) {
                BY_JENIS.put(pj.getJenis(), pj);
            }
        }

        @JsonIgnore
        public static Penjamin getPenjamin(String jenis) {
            if(BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new IllegalStateException("Penjamin tidak sesuai");
        }
    }
}
