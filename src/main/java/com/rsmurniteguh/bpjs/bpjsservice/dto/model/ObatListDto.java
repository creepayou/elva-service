package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObatListDto {
    @JsonAlias("obat")
    private List<ObatDto> list;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(value = Include.NON_NULL)
    private static class ObatDto {
        private String jmlObat;
        private String nmObat;
        private String signa;
    }
}
