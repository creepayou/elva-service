package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {

    @AllArgsConstructor
    public enum JenisPelayanan {
        RAWAT_INAP(new VClaimMappingDto().setKode("1").setNama("R.Inap"), "Rawat Inap"),
        RAWAT_JALAN(new VClaimMappingDto().setKode("2").setNama("R.Jalan"), "Rawat Jalan");

        @JsonValue
        @Getter
        private VClaimMappingDto jenis;
        @Getter
        private String jenisText;

        @JsonCreator
        public static JenisPelayanan fromValue(String value) throws BpjsServiceException{
            return getJenisPelayanan(value);
        }

        @JsonIgnore
        private static final Map<String, JenisPelayanan> BY_JENIS = new HashMap<>();

        static {
            for (JenisPelayanan jp : values()) {
                BY_JENIS.put(jp.getJenis().getKode(), jp);
                BY_JENIS.put(jp.getJenis().getNama(), jp);
                BY_JENIS.put(jp.getJenisText(), jp);
            }
        }

        @JsonIgnore
        public static JenisPelayanan getJenisPelayanan(String jenis) throws BpjsServiceException {
            if (BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new BpjsServiceException("Jenis Pelayanan tidak sesuai");
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
        public static TipeRujukan getTipeRujukan(String tipe) throws BpjsServiceException {
            if (BY_TIPE.containsKey(tipe))
                return BY_TIPE.get(tipe);
            else
                throw new BpjsServiceException("Tipe Rujukan tidak sesuai");
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
        public static KelasRawat fromValue(String value) throws BpjsServiceException{
            return getKelasRawat(value);
        }

        @JsonIgnore
        public static KelasRawat getKelasRawat(String kelas) throws BpjsServiceException {
            if(BY_KELAS.containsKey(kelas))
                return BY_KELAS.get(kelas);
            else
                throw new BpjsServiceException("Kelas Rawat tidak sesuai");
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
        public static Faskes getJenisFaskes(String jenis) throws BpjsServiceException {
            if(BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new BpjsServiceException("Faskes tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Indikator {
        TIDAK(new VClaimMappingDto().setKode("0").setNama("Tidak")), YA(new VClaimMappingDto().setKode("1").setNama("Ya"));

        @JsonValue
        @Getter
        private VClaimMappingDto ind;

        @JsonIgnore
        private static final Map<String, Indikator> BY_IND = new HashMap<>();

        static {
            for (Indikator ind : values()) {
                BY_IND.put(ind.getInd().getKode(), ind);
                BY_IND.put(ind.getInd().getNama(), ind);
            }
        }

        @JsonIgnore
        public static Indikator getIndikator(String ind) throws BpjsServiceException {
            if(BY_IND.containsKey(ind))
                return BY_IND.get(ind);
            else
                throw new BpjsServiceException("Jenis Pelayanan tidak sesuai");
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
        public static StatusKlaim getStatusKlaim(String status) throws BpjsServiceException {
            if(BY_STATUS.containsKey(status))
                return BY_STATUS.get(status);
            else
                throw new BpjsServiceException("Status Klaim tidak sesuai");
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
        public static Penjamin getPenjamin(String jenis) throws BpjsServiceException {
            if(BY_JENIS.containsKey(jenis))
                return BY_JENIS.get(jenis);
            else
                throw new BpjsServiceException("Penjamin tidak sesuai");
        }
    }
}
