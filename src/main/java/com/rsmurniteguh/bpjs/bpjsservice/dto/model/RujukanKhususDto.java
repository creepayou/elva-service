package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

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
    private String idrujukan;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglrujukan_awal;
    private String norujukan;
    private String nokapst;
    private String nmpst;
    private String diagppk;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglrujukan_akhir;
}
