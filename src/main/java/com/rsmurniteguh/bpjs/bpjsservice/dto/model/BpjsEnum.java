package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BpjsServiceException;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {

    @AllArgsConstructor
    public enum JenisPelayanan {
        RAWAT_INAP(new VClaimMappingDto("1", "Rawat Inap"), "R.Inap"),
        RAWAT_JALAN(new VClaimMappingDto("2","Rawat Jalan"), "R.Jalan");

        @Getter
        private VClaimMappingDto jenis;
        protected String jenisText;

        @JsonCreator
        public static JenisPelayanan fromValue(String value) throws BpjsServiceException{
            return getJenisPelayanan(value);
        }

        private static final Map<String, JenisPelayanan> BY_VALUE = new HashMap<>();

        static {
            for (JenisPelayanan jp : values()) {
                BY_VALUE.put(jp.getJenis().getKode(), jp);
                BY_VALUE.put(jp.getJenis().getNama(), jp);
                BY_VALUE.put(jp.jenisText, jp);
                BY_VALUE.put(jp.name(), jp);
            }
        }

        @JsonIgnore
        public static JenisPelayanan getJenisPelayanan(String jenis) throws BpjsServiceException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BpjsServiceException("Jenis Pelayanan tidak sesuai");
        }
    }

    public enum JenisRujukan {
        PCARE, RS
    }

    @AllArgsConstructor
    public enum TipeRujukan {
        PENUH("1"), PARTIAL("2"), RUJUK_BALIK("3");

        @Getter
        private String tipe;
        
        @JsonCreator
        public static TipeRujukan fromValue(String value) throws BpjsServiceException{
            return getTipeRujukan(value);
        }

        @JsonIgnore
        private static final Map<String, TipeRujukan> BY_TIPE = new HashMap<>();

        static {
            for (TipeRujukan tr : values()) {
                BY_TIPE.put(tr.getTipe(), tr);
                BY_TIPE.put(tr.name(), tr);
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
        KELAS_I(new VClaimMappingDto("1","Kelas 1")), 
        KELAS_II(new VClaimMappingDto("2","Kelas 2")), 
        KELAS_III(new VClaimMappingDto("3","Kelas 3")),
        NONE(new VClaimMappingDto("-","Tidak Ada"));

        @Getter
        private VClaimMappingDto kelas;
        
        @JsonCreator
        public static KelasRawat fromValue(String value) throws BpjsServiceException{
            return getKelasRawat(value);
        }

        @JsonIgnore
        private static final Map<String, KelasRawat> BY_VALUE = new HashMap<>();

        static {
            for (KelasRawat kr : values()) {
                BY_VALUE.put(kr.getKelas().getKode(), kr);
                BY_VALUE.put(kr.getKelas().getNama(), kr);
                BY_VALUE.put(kr.name(), kr);
            }
        }

        @JsonIgnore
        public static KelasRawat getKelasRawat(String kelas) throws BpjsServiceException {
            if(BY_VALUE.containsKey(kelas))
                return BY_VALUE.get(kelas);
            else
                throw new BpjsServiceException("Kelas Rawat tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Faskes {
        FASKES_1("1"), FASKES_2("2");

        @Getter
        private String jenis;

        @JsonCreator
        public static Faskes fromValue(String value) throws BpjsServiceException{
            return getJenisByValue(value);
        }

        @JsonIgnore
        private static final Map<String, Faskes> BY_VALUE = new HashMap<>();

        static {
            for (Faskes fk : values()) {
                BY_VALUE.put(fk.getJenis(), fk);
                BY_VALUE.put(fk.name(), fk);
            }
        }

        @JsonIgnore
        public static Faskes getJenisByValue(String jenis) throws BpjsServiceException {
            if(BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BpjsServiceException("Faskes tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Indikator {
        TIDAK(new VClaimMappingDto("0","Tidak")), 
        YA(new VClaimMappingDto("1","Ya"));

        @Getter
        private VClaimMappingDto ind;

        @JsonCreator
        public static Indikator fromValue(String value) throws BpjsServiceException{
            return getIndByValue(value);
        }

        private static final Map<String, Indikator> BY_VALUE = new HashMap<>();

        static {
            for (Indikator ind : values()) {
                BY_VALUE.put(ind.getInd().getKode(), ind);
                BY_VALUE.put(ind.getInd().getNama(), ind);
                BY_VALUE.put(ind.name(), ind);
            }
        }

        @JsonIgnore
        public static Indikator getIndByValue(String ind) throws BpjsServiceException {
            if(BY_VALUE.containsKey(ind))
                return BY_VALUE.get(ind);
            else
                throw new BpjsServiceException("Jenis Pelayanan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum StatusKlaim {
        PROSES_VERIFIKASI(new VClaimMappingDto("1","Proses Verifikasi")), 
        PENDING_VERIFIKASI(new VClaimMappingDto("2","Pending Verifikasi")), 
        KLAIM(new VClaimMappingDto("3","Klaim"));

        @Getter
        private VClaimMappingDto status;

        @JsonCreator
        public static StatusKlaim fromValue(String value) throws BpjsServiceException{
            return getStatusByValue(value);
        }

        private static final Map<String, StatusKlaim> BY_VALUE = new HashMap<>();

        static {
            for (StatusKlaim sts : values()) {
                BY_VALUE.put(sts.getStatus().getKode(), sts);
                BY_VALUE.put(sts.getStatus().getNama(), sts);
                BY_VALUE.put(sts.name(), sts);
            }
        }

        @JsonIgnore
        public static StatusKlaim getStatusByValue(String status) throws BpjsServiceException {
            if(BY_VALUE.containsKey(status))
                return BY_VALUE.get(status);
            else
                throw new BpjsServiceException("Status Klaim tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Penjamin {
        JASA_RAHARJA("1"), BPJS_TK("2"), TASPEN("3"), ASABRI("4");

        @Getter
        private String jenis;

        @JsonCreator
        public static Penjamin fromValue(String value) throws BpjsServiceException{
            return getJenisByValue(value);
        }

        private static final Map<String, Penjamin> BY_VALUE = new HashMap<>();

        static {
            for (Penjamin pj : values()) {
                BY_VALUE.put(pj.getJenis(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static Penjamin getJenisByValue(String jenis) throws BpjsServiceException {
            if(BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BpjsServiceException("Penjamin tidak sesuai");
        }
    }
}
