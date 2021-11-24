package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.StatusPulang;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUpdateTglPulangDto {
    private String noSep;
    private Timestamp tglPlg;
    private String ppkPelayanan;
    private StatusPulang statusPulang;
    private String noSuratMeninggal;
    private Timestamp tglPulang;
    private String noLPManual;
    private String user;
}
