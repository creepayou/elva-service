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
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RujukanDto {
    private VClaimMappingDto diagnosa;
    private String keluhan;
    private String noKunjungan;
    private VClaimMappingDto pelayanan;
    private BpjsResponsePesertaDto peserta;
    private VClaimMappingDto poliRujukan;
    private VClaimMappingDto provPerujuk;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglKunjungan;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRujukan;
}
