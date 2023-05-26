package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DokterDPJPDto {
    @JsonAlias("kdDPJP")
    private String kdDokter;
    @JsonAlias("nmDPJP")
    private String nmDokter;
    private String noSurat;
}
