package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp tglKunjungan;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp tglRujukan;
}
