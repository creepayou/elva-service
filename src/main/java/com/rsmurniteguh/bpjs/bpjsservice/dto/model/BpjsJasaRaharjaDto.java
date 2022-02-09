package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsJasaRaharjaDto {
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglKejadian;
    private String noRegister;
    private String ketStatusDijamin;
    private String ketStatusDikirim;
    private BigDecimal biayaDijamin;
    private BigDecimal plafon;
    private BigDecimal jmlDibayar;
    private String resultsJasaRaharja;
}
