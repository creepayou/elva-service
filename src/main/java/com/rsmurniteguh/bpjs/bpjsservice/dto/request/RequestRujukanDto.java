package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class RequestRujukanDto {
    private String noRujukan;
    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Timestamp tglRujukan;
    private String ppkDirujuk;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class)
    private JenisPelayanan jnsPelayanan;
    private String catatan;
    private String diagRujukan;
    private String tipeRujukan;
    private String poliRujukan;
    private String user;
}
