package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rsmurniteguh.bpjs.bpjsservice.exception.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BpjsEnum {

    @AllArgsConstructor
    public enum JenisPelayanan {
        RAWAT_INAP(new VClaimMappingDto("1", "Rawat Inap"), "R.Inap"),
        RAWAT_JALAN(new VClaimMappingDto("2", "Rawat Jalan"), "R.Jalan");

        @Getter
        private VClaimMappingDto jenis;
        protected String jenisText;

        @JsonCreator
        public static JenisPelayanan fromValue(String value) throws BusinessException {
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
        public static JenisPelayanan getJenisPelayanan(String jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Jenis Pelayanan tidak sesuai");
        }
    }

    public enum JenisRujukan {
        PCARE, RS
    }

    @AllArgsConstructor
    public enum TipeRujukan {
        PENUH("0"), PARTIAL("1"), RUJUK_BALIK("2");

        @Getter
        private String tipe;

        @JsonCreator
        public static TipeRujukan fromValue(String value) throws BusinessException {
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
        public static TipeRujukan getTipeRujukan(String tipe) throws BusinessException {
            if (BY_TIPE.containsKey(tipe))
                return BY_TIPE.get(tipe);
            else
                throw new BusinessException("Tipe Rujukan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum KelasRawat {
        KELAS_I(new VClaimMappingDto("1", "Kelas 1")), KELAS_II(new VClaimMappingDto("2", "Kelas 2")),
        KELAS_III(new VClaimMappingDto("3", "Kelas 3")), NONE(new VClaimMappingDto("-", "Tidak Ada"));

        @Getter
        private VClaimMappingDto kelas;

        @JsonCreator
        public static KelasRawat fromValue(String value) throws BusinessException {
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
        public static KelasRawat getKelasRawat(String kelas) throws BusinessException {
            if (BY_VALUE.containsKey(kelas))
                return BY_VALUE.get(kelas);
            else
                throw new BusinessException("Kelas Rawat tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Faskes {
        FASKES_1("1"), FASKES_2("2");

        @Getter
        private String jenis;

        @JsonCreator
        public static Faskes fromValue(String value) throws BusinessException {
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
        public static Faskes getJenisByValue(String jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Faskes tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Indikator {
        TIDAK(new VClaimMappingDto("0", "Tidak")), YA(new VClaimMappingDto("1", "Ya"));

        @Getter
        private VClaimMappingDto ind;

        @JsonCreator
        public static Indikator fromValue(String value) throws BusinessException {
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
        public static Indikator getIndByValue(String ind) throws BusinessException {
            if (BY_VALUE.containsKey(ind))
                return BY_VALUE.get(ind);
            else if (ind.equals("-"))
                return Indikator.TIDAK;
            else
                throw new BusinessException("Indikator tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum StatusKlaim {
        PROSES_VERIFIKASI(new VClaimMappingDto("1", "Proses Verifikasi")),
        PENDING_VERIFIKASI(new VClaimMappingDto("2", "Pending Verifikasi")), KLAIM(new VClaimMappingDto("3", "Klaim"));

        @Getter
        private VClaimMappingDto status;

        @JsonCreator
        public static StatusKlaim fromValue(String value) throws BusinessException {
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
        public static StatusKlaim getStatusByValue(String status) throws BusinessException {
            if (BY_VALUE.containsKey(status))
                return BY_VALUE.get(status);
            else
                throw new BusinessException("Status Klaim tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Penjamin {
        JASA_RAHARJA("1"), BPJS_TK("2"), TASPEN("3"), ASABRI("4");

        @Getter
        private String jenis;

        @JsonCreator
        public static Penjamin fromValue(String value) throws BusinessException {
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
        public static Penjamin getJenisByValue(String jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Penjamin tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum VclaimVersion {
        V1_1("1.1"), V2("2.0");

        @Getter
        private String version;

        @JsonCreator
        public static VclaimVersion fromVersion(String version) throws BusinessException {
            return getVersion(version);
        }

        private static final Map<String, VclaimVersion> BY_VERSION = new HashMap<>();

        static {
            for (VclaimVersion v : values()) {
                BY_VERSION.put(v.getVersion(), v);
                BY_VERSION.put(v.name(), v);
            }
        }

        @JsonIgnore
        public static VclaimVersion getVersion(String version) throws BusinessException {
            if (BY_VERSION.containsKey(version))
                return BY_VERSION.get(version);
            else
                throw new BusinessException("Version tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum JenisKontrol {
        SPRI("1"), RENCANA_KONTROL("2");

        @Getter
        private String jenis;

        @JsonCreator
        public static JenisKontrol fromValue(String value) throws BusinessException {
            return getJenisByValue(value);
        }

        private static final Map<String, JenisKontrol> BY_VALUE = new HashMap<>();

        static {
            for (JenisKontrol pj : values()) {
                BY_VALUE.put(pj.getJenis(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static JenisKontrol getJenisByValue(String jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Jenis kontrol tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum FilterTanggalRencanaKontrol {
        TANGGAL_ENTRI("1"), TANGGAL_RENCANA_KONTROL("2");

        @Getter
        private String filter;

        @JsonCreator
        public static FilterTanggalRencanaKontrol fromValue(String value) throws BusinessException {
            return getFilterByValue(value);
        }

        private static final Map<String, FilterTanggalRencanaKontrol> BY_VALUE = new HashMap<>();

        static {
            for (FilterTanggalRencanaKontrol pj : values()) {
                BY_VALUE.put(pj.getFilter(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static FilterTanggalRencanaKontrol getFilterByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Filter tanggal rencana kontrol tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum StatusPulang {
        PERSETUJUAN_DOKTER("1"), DIRUJUK("2"), PERMINTAAN_SENDIRI("3"), MENINGGAL("4"), LAIN_LAIN("5");

        @Getter
        private String status;

        @JsonCreator
        public static StatusPulang fromValue(String value) throws BusinessException {
            return getFilterByValue(value);
        }

        private static final Map<String, StatusPulang> BY_VALUE = new HashMap<>();

        static {
            for (StatusPulang pj : values()) {
                BY_VALUE.put(pj.getStatus(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static StatusPulang getFilterByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Status pulang tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum TujuanKunjungan {
        NORMAL("0"), PROSEDUR("1"), KONSUL_DOKTER("2");

        @Getter
        private String tujuan;

        @JsonCreator
        public static TujuanKunjungan fromValue(String value) throws BusinessException {
            return getTujuanByValue(value);
        }

        private static final Map<String, TujuanKunjungan> BY_VALUE = new HashMap<>();

        static {
            for (TujuanKunjungan pj : values()) {
                BY_VALUE.put(pj.getTujuan(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static TujuanKunjungan getTujuanByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Tujuan kunjungan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum FlagProcedure {
        TIDAK_BERKELANJUTAN("0"), BERKELANJUTAN("1"), NONE("");

        @Getter
        private String flag;

        @JsonCreator
        public static FlagProcedure fromValue(String value) throws BusinessException {
            return getFlagByValue(value);
        }

        private static final Map<String, FlagProcedure> BY_VALUE = new HashMap<>();

        static {
            for (FlagProcedure pj : values()) {
                BY_VALUE.put(pj.getFlag(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static FlagProcedure getFlagByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Flag procedure tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum KodePenunjang {
        RADIOTERAPI("1"), KEMOTERAPI("2"), REHAB_MEDIK("3"), REHAB_PSIKOSOSIAL("4"), TRANSFUSI_DARAH("5"),
        PELAYANAN_GIGI("6"), LABORATORIUM("7"), USG("8"), FARMASI("9"), LAIN_LAIN("10"), MRI("11"), HEMODIALISA("12"),
        NONE("");

        @Getter
        private String kode;

        @JsonCreator
        public static KodePenunjang fromValue(String value) throws BusinessException {
            return getKodeByValue(value);
        }

        private static final Map<String, KodePenunjang> BY_VALUE = new HashMap<>();

        static {
            for (KodePenunjang pj : values()) {
                BY_VALUE.put(pj.getKode(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static KodePenunjang getKodeByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Kode penunjang tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum AssesmentPel {
        POLI_TIDAK_TERSEDIA("1"), JAM_POLI_BERAKHIR("2"), DOKTER_TIDAK_PRAKTEK("3"), ATAS_INSTRUKSI_RS("4"),
        TUJUAN_KONTROL("5"), NONE("");

        @Getter
        private String assesmen;

        @JsonCreator
        public static AssesmentPel fromValue(String value) throws BusinessException {
            return getAssesmentPelByValue(value);
        }

        private static final Map<String, AssesmentPel> BY_VALUE = new HashMap<>();

        static {
            for (AssesmentPel pj : values()) {
                BY_VALUE.put(pj.getAssesmen(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static AssesmentPel getAssesmentPelByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Assesmen pel tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Pembiayaan {
        PRIBADI("1"), PEMBERI_KERJA("2"), ASURANSI("3"), NONE("");

        @Getter
        private String jenis;

        @JsonCreator
        public static Pembiayaan fromValue(String value) throws BusinessException {
            return getJenisByValue(value);
        }

        private static final Map<String, Pembiayaan> BY_VALUE = new HashMap<>();

        static {
            for (Pembiayaan pj : values()) {
                BY_VALUE.put(pj.getJenis(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static Pembiayaan getJenisByValue(String filter) throws BusinessException {
            if (BY_VALUE.containsKey(filter))
                return BY_VALUE.get(filter);
            else
                throw new BusinessException("Pembiayaan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum JenisPengajuan {
        BACKDATE("1"), FINGER_PRINT("2");

        @Getter
        private String jenis;

        @JsonCreator
        public static JenisPengajuan fromValue(String value) throws BusinessException {
            return getJenisPengajuan(value);
        }

        private static final Map<String, JenisPengajuan> BY_VALUE = new HashMap<>();

        static {
            for (JenisPengajuan jp : values()) {
                BY_VALUE.put(jp.getJenis(), jp);
                BY_VALUE.put(jp.name(), jp);
            }
        }

        @JsonIgnore
        public static JenisPengajuan getJenisPengajuan(String jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Jenis Pengajuan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum Lakalantas {
        BKLL(new VClaimMappingDto("0", "Bukan Kecelakaan Lalu Lintas (BKLL)")),
        KLL_BKK(new VClaimMappingDto("1", "Kecelakaan Lalu Lintas (KLL) dan Bukan Kecelakaan Kerja (BKK)")),
        KLL_KK(new VClaimMappingDto("2", "KLL dan KK")), KK(new VClaimMappingDto("3", "Kecelakaan Kerja (KK)"));

        @Getter
        private VClaimMappingDto kll;

        @JsonCreator
        public static Lakalantas fromValue(String value) throws BusinessException {
            return getLakalantasByValue(value);
        }

        private static final Map<String, Lakalantas> BY_VALUE = new HashMap<>();

        static {
            for (Lakalantas sts : values()) {
                BY_VALUE.put(sts.getKll().getKode(), sts);
                BY_VALUE.put(sts.getKll().getNama(), sts);
                BY_VALUE.put(sts.name(), sts);
            }
        }

        @JsonIgnore
        public static Lakalantas getLakalantasByValue(String status) throws BusinessException {
            if (BY_VALUE.containsKey(status))
                return BY_VALUE.get(status);
            else
                throw new BusinessException("Lakalantas tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum BpjsInfoType {
        CARD("CARD"), NIK("NIK"), REFERENCE_I("REF1"), REFERENCE_II("REF2");

        @Getter
        private String bpjsInfo;

        @JsonCreator
        public static BpjsInfoType fromValue(String value) throws BusinessException {
            return getBpjsInfoTypeByValue(value);
        }

        @JsonIgnore
        private static final Map<String, BpjsInfoType> BY_VALUE = new HashMap<>();

        static {
            for (BpjsInfoType fk : values()) {
                BY_VALUE.put(fk.getBpjsInfo(), fk);
                BY_VALUE.put(fk.name(), fk);
            }
        }

        @JsonIgnore
        public static BpjsInfoType getBpjsInfoTypeByValue(String bpjsInfo) throws BusinessException {
            if (BY_VALUE.containsKey(bpjsInfo))
                return BY_VALUE.get(bpjsInfo);
            else
                throw new BusinessException("BpjsInfoType tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum JenisKunjungan {
        RUJUKAN_FKTP(1), RUJUKAN_INTERNAL(2), KONTROL(3), RUJUKAN_ANTAR_RS(4);

        @Getter
        private Integer jenis;

        @JsonCreator
        public static JenisKunjungan fromValue(Object value) throws BusinessException {
            return getJenisByValue(value);
        }

        private static final Map<Object, JenisKunjungan> BY_VALUE = new HashMap<>();

        static {
            for (JenisKunjungan pj : values()) {
                BY_VALUE.put(pj.getJenis(), pj);
                BY_VALUE.put(pj.name(), pj);
            }
        }

        @JsonIgnore
        public static JenisKunjungan getJenisByValue(Object jenis) throws BusinessException {
            if (BY_VALUE.containsKey(jenis))
                return BY_VALUE.get(jenis);
            else
                throw new BusinessException("Jenis Kunjungan tidak sesuai");
        }
    }

    @AllArgsConstructor
    public enum FilterWaktuAntrean {
        RS("rs"), SERVER("server");

        @Getter
        private String waktu;
    }

    @AllArgsConstructor
    public enum TaskIdAntrean {
        TUNGGU_ADMISI(1), MULAI_LAYANAN_ADMISI(2), TUNGGU_POLI(3),
        MULAI_LAYANAN_POLI(4), TUNGGU_FARMASI(5), MULAI_LAYANAN_FARMASI(6),
        SELESAI_FARMASI(7), BATAL_ANTRIAN(99);

        @Getter
        private Integer taskId;

        @JsonCreator
        public static TaskIdAntrean fromValue(Integer value) throws BusinessException {
            return getTaskIdAntrean(value);
        }

        @JsonIgnore
        private static final Map<Integer, TaskIdAntrean> BY_VALUE = new HashMap<>();

        static {
            for (TaskIdAntrean taskIdAntrean : values()) {
                BY_VALUE.put(taskIdAntrean.getTaskId(), taskIdAntrean);
            }
        }

        @JsonIgnore
        public static TaskIdAntrean getTaskIdAntrean(Integer value) throws BusinessException {
            if (BY_VALUE.containsKey(value))
                return BY_VALUE.get(value);
            else
                throw new BusinessException("Task Id tidak sesuai");
        }
    }
}
