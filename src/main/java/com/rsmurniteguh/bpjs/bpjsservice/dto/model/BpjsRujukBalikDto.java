package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

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
    private VClaimMappingDto DPJP;
    private String keterangan;
    private String noSEP;
    private String noSRB;
    private String obat;
    private PesertaDto peserta;
    private VClaimMappingDto programPRB;
    private String saran;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private String tglSRB;
}
