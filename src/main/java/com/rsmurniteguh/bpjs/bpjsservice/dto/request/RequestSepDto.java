package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.base.constant.Constant;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Indikator;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsPesertaDto.Asuransi;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestSepDto {
    private String noKartu;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = Constant.TIMEZONE_JKT)
    private Timestamp tglSep;
    private String ppkPelayanan;
    private JenisPelayanan jnsPelayanan;
    private String klsRawat;
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
    private String user;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Rujukan{
    private Faskes asalRujukan;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = Constant.TIMEZONE_JKT)
    private Timestamp tglRujukan;
    private String noRujukan;
    private String ppkRujukan;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Poli{
    private String tujuan;
    private Indikator eksekutif;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class KatarakInd{
    private Indikator katarak;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class Jaminan{
    private Indikator lakaLantas;
    private PenjaminJaminan penjamin;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class PenjaminJaminan{
    private String penjamin;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = Constant.TIMEZONE_JKT)
    private Timestamp tglKejadian;
    private String keterangan;
}

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
final class SuplesiJaminan{
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