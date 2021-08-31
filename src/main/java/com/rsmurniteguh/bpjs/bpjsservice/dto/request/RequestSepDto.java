package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateTimeSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsResponsePesertaDto.Asuransi;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestSepDto {
    private String noKartu;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglSep;
    @JsonSerialize(using = CustomJsonDateTimeSerializer.class)
    private Timestamp tglPulangSep;
    private String ppkPelayanan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private JenisPelayanan jnsPelayanan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private KelasRawat klsRawat;
    private String noMR;
    private Rujukan rujukan;
    private String catatan;
    private String diagAwal;
    private Asuransi cob;
    private KatarakInd katarak;
    private Jaminan jaminan;
    private SKDP skdp;
    private String noTelp;
    private String noSep;
    private String keterangan;
    private String user;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Rujukan{
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private Faskes asalRujukan;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglRujukan;
    private String noRujukan;
    private String ppkRujukan;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Poli{
    private String tujuan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private Indikator eksekutif;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class KatarakInd{
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private Indikator katarak;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Jaminan{
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private Indikator lakaLantas;
    private PenjaminJaminan penjamin;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class PenjaminJaminan{
    private String penjamin;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglKejadian;
    private String keterangan;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class SuplesiJaminan{
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private Indikator suplesi;
    private String noSepSuplesi;
    private LokasiLakalantas lokasiLaka;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class LokasiLakalantas{
    private String kdPropinsi;
    private String kdKabupaten;
    private String kdKecamatan;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class SKDP{
    private String noSurat;
    private String kodeDPJP;
}