package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateTimeSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusPulang;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUpdateTglPulangDto {
    private String noSep;
    @JsonSerialize(using = CustomJsonDateTimeSerializer.class)
    private Timestamp tglPlg;
    private String ppkPelayanan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private StatusPulang statusPulang;
    private String noSuratMeninggal;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglPulang;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglMeninggal;
    private String noLPManual;
    private String user;
}
