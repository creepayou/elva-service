package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateTimeSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomNullSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.AssesmentPel;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.FlagProcedure;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KodePenunjang;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Lakalantas;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Penjamin;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TujuanKunjungan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsKelasRawatDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestSepDtoV2 {
    private String noKartu;
    @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Timestamp tglSep;
    @JsonSerialize(using = CustomJsonDateTimeSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Timestamp tglPulangSep;
    private String ppkPelayanan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private JenisPelayanan jnsPelayanan;
    private BpjsKelasRawatDto klsRawat;
    private String noMR;
    private Rujukan rujukan;
    private String catatan;
    private String diagAwal;
    private Asuransi cob;
    private KatarakInd katarak;
    private Poli poli;
    private Jaminan jaminan;
    private SKDP skdp;
    private String noTelp;
    private String noSep;
    private String keterangan;
    private String user;
    private String dpjpLayan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private TujuanKunjungan tujuanKunj;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private FlagProcedure flagProcedure;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private KodePenunjang kdPenunjang;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private AssesmentPel assesmentPel;

    @Data
    @Accessors(chain = true)
    public static class Rujukan{
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Faskes asalRujukan;
        @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Timestamp tglRujukan;
        private String noRujukan;
        private String ppkRujukan;
    }

    @Data
    @Accessors(chain = true)
    public static class Poli{
        private String tujuan;
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Indikator eksekutif;
    }

    @Data
    @Accessors(chain = true)
    public static class KatarakInd{
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Indikator katarak;
    }

    @Data
    @Accessors(chain = true)
    public static class Jaminan{
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Lakalantas lakaLantas;
        private PenjaminJaminan penjamin;
    }

    @Data
    @Accessors(chain = true)
    public static class PenjaminJaminan{
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Penjamin penjamin;
        @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Timestamp tglKejadian;
        @JsonSerialize(nullsUsing = CustomNullSerializer.class)
        private String keterangan;
        private SuplesiJaminan suplesi;
    }

    @Data
    @Accessors(chain = true)
    public static class SuplesiJaminan{
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Indikator suplesi;
        @JsonSerialize(nullsUsing = CustomNullSerializer.class)
        private String noSepSuplesi;
        private LokasiLakalantas lokasiLaka;
    }

    @Data
    @Accessors(chain = true)
    public static class LokasiLakalantas{
        @JsonSerialize(nullsUsing = CustomNullSerializer.class)
        private String kdPropinsi;
        @JsonSerialize(nullsUsing = CustomNullSerializer.class)
        private String kdKabupaten;
        @JsonSerialize(nullsUsing = CustomNullSerializer.class)
        private String kdKecamatan;
    }

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Asuransi {
        @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
        private Indikator cob;
    }

    @Data
    @Accessors(chain = true)
    public static class SKDP{
        private String noSurat;
        private String kodeDPJP;
    }

}