package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class BpjsSepKllDto {
    private String noSEP;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglKejadian;
    private String ppkPelSEP;
    private String kdProp;
    private String kdKab;
    private String kdKec;
    private String ketKejadian;
    private String noSEPSuplesi;
}
