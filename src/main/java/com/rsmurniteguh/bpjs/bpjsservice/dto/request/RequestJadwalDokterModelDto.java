package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.JadwalDto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestJadwalDokterModelDto {
    private String kodePoli;
    private String kodeSubSpesialis;
    private Long kodeDokter;
    private List<JadwalDto>jadwal;
    
}

