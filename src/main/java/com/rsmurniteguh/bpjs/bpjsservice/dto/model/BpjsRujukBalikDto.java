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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class BpjsRujukBalikDto {
    @JsonAlias("DPJP")
    private VClaimMappingDto dpjp;
    private String keterangan;
    private String noSEP;
    private String noSRB;
    private PesertaDto peserta;
    private VClaimMappingDto programPRB;
    @JsonAlias({ "programPRB" })
    private String programPRBString;
    private String saran;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSRB;
    private ObatListDto obat;
}
