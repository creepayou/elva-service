package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateDeserializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LokasiKejadianDto {
    private String kdProp;
    private String kdKab;
    private String kdKec;
    private String ketKejadian;
    private String lokasi;
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Timestamp tglKejadian;
}
