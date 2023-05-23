package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsAntreanPoliDto {
    @JsonAlias({"nmpoli", "namapoli"})
    private String namaPoli;
    @JsonAlias({"nmsubspesialis", "namasubspesialis"})
    private String namaSubspesialis;
    @JsonAlias({"kdpoli", "kodepoli"})
    private String kodePoli;
    @JsonAlias({"kdsubspesialis", "kodesubspesialis"})
    private String kodeSubspesialis;
}
