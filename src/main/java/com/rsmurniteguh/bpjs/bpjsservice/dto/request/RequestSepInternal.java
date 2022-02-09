package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomNullSerializer;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestSepInternal {
    private String noSep;
    private String noSurat;
    @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Timestamp tglRujukanInternal;
    private String kdPoliTuj;
    private String user;
}
