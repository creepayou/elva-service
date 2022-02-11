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
public class RujukanKhususDto{
    @JsonAlias("idrujukan")
    private String idRujukan;
    @JsonAlias("norujukan")
    private String noRujukan;
    @JsonAlias("nokapst")
    private String noKartu;
    @JsonAlias("nmpst")
    private String nama;
    @JsonAlias("diagppk")
    private String diagPPK;
    @JsonAlias("tglrujukan_awal")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRujukanAwal;
    @JsonAlias("tglrujukan_berakhir")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRujukanBerakhir;
}
