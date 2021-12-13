package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class BpjsSepSuplesiDto {
    private String noRegister;
    private String noSep;
    private String noSepAwal;
    private String noSuratJaminan;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglKejadian;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglSep;
}
