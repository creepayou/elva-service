package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateTimeSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.Asuransi;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.Jaminan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.KatarakInd;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.Poli;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.Rujukan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.request.RequestSepDtoV2.SKDP;

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
    private Poli poli;
    private Jaminan jaminan;
    private SKDP skdp;
    private String noTelp;
    private String noSep;
    private String keterangan;
    private String user;

}