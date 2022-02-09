package com.rsmurniteguh.bpjs.bpjsservice.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomBpjsEnumSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.config.CustomNullSerializer;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.KelasRawat;
import com.rsmurniteguh.bpjs.bpjsservice.dto.model.BpjsEnum.Pembiayaan;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BpjsKelasRawatDto {
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private KelasRawat klsRawatHak;
    @JsonSerialize(nullsUsing = CustomNullSerializer.class)
    private String klsRawatNaik;
    @JsonSerialize(using = CustomBpjsEnumSerializer.class, nullsUsing = CustomNullSerializer.class)
    private Pembiayaan pembiayaan;
    @JsonSerialize(nullsUsing = CustomNullSerializer.class)
    private String penanggungJawab;
}
