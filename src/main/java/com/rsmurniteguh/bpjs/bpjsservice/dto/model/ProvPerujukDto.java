package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Faskes;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvPerujukDto {
    private String kdProviderPerujuk;
    private String nmProviderPerujuk;
    private Faskes asalRujukan;
    private String noRujukan;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglRujukan;
}
