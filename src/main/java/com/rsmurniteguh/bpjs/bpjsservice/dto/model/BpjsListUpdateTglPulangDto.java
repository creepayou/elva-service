package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsListUpdateTglPulangDto {
    private String noSep;
    private String noSepUpdating;
    private JenisPelayanan jnsPelayanan;
    private String ppkTujuan;
    private String noKartu;
    private String nama;

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglPulang;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSep;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglMeninggal;

    private String noSurat;
    private String keterangan;
    private String user;
    private String status;
}

