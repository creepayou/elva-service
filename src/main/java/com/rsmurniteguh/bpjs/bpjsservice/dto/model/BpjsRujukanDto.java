package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsRujukanDto {
    
    private VClaimMappingDto diagnosa;
    private String keluhan;
    private String noKunjungan;
    private VClaimMappingDto pelayanan;
    private BpjsPesertaDto peserta;
    private VClaimMappingDto poliRujukan;
    private VClaimMappingDto provPerujuk;
    private VClaimMappingDto poliTujuan;
    private VClaimMappingDto tunjuanRujukan;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglKunjungan;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglRujukan;
}
