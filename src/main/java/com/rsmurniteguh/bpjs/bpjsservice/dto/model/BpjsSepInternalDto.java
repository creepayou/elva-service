package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsSepInternalDto {
    @JsonAlias("tujuanrujuk")
    private String tujuanRujuk;
    @JsonAlias("nmtujuanrujuk")
    private String nmTujuanRujuk;
    @JsonAlias("nmpoliasal")
    private String nmPoliAsal;
    @JsonAlias("tglrujukinternal")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRujukInternal;
    @JsonAlias("nosep")
    private String noSep;
    @JsonAlias("nosepref")
    private String noSepRef;
    @JsonAlias("ppkpelsep")
    private String ppkPelSep;
    @JsonAlias("nokapst")
    private String noKaPst;
    @JsonAlias("tglsep")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSep;
    @JsonAlias("nosurat")
    private String noSurat;
    @JsonAlias("flaginternal")
    private String flagInternal;
    @JsonAlias("kdpoliasal")
    private String kdPoliAsal;
    @JsonAlias("kdpolituj")
    private String kdPoliTuj;
    @JsonAlias("kdpenunjang")
    private String kdPenunjang;
    @JsonAlias("nmpenunjang")
    private String nmPenunjang;
    @JsonAlias("diagppk")
    private String diagPpk;
    @JsonAlias("kddokter")
    private String kdDokter;
    @JsonAlias("nmdokter")
    private String nmDokter;
    @JsonAlias("flagprosedur")
    private String flagProsedur;
    @JsonAlias("opsikonsul")
    private String opsiKonsul;
    @JsonAlias("flagsep")
    private String flagSep;
    @JsonAlias("fuser")
    private String fUser;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp fdate;
    @JsonAlias("nmdiag")
    private String nmDiag;
}
