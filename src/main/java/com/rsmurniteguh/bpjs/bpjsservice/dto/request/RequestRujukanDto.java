package com.rsmurniteguh.bpjs.bpjsservice.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomJsonDateSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomNullSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.JenisPelayanan;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.TipeRujukan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
public class RequestRujukanDto {
    private String noSep;
    private String noRujukan;
    @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Timestamp tglRujukan;
    @JsonSerialize(using = CustomJsonDateSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Timestamp tglRencanaKunjungan;
    private String ppkDirujuk;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private JenisPelayanan jnsPelayanan;
    private String catatan;
    private String diagRujukan;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private TipeRujukan tipeRujukan;
    private String poliRujukan;
    private String user;
}
